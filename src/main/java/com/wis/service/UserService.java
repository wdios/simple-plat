package com.wis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.ehcache.config.Searchable;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wis.exception.user.UserBlockedException;
import com.wis.exception.user.UserNotExistsException;
import com.wis.exception.user.UserPasswordNotMatchException;
import com.wis.model.user.User;
import com.wis.model.user.UserOrganizationJob;
import com.wis.model.user.UserStatus;
import com.wis.tookit.PasswordHelper;
import com.wis.tookit.UserLogUtils;
import com.wis.tookit.sql.SqlHelper;

/**
 * 用户相关操作
 */
@Service
public class UserService {

    @Autowired
    private UserStatusHistoryService userStatusHistoryService;
    @Autowired
	private JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordService passwordService;
    
    public static final int USERNAME_TYPE = 1;
    public static final int EMAIL_TYPE = 2;
    public static final int PHONE_TYPE = 3;

    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    public User save(User user) {
    	PasswordHelper aPasswordHelper = new PasswordHelper();
    	aPasswordHelper.encryptPassword(user);
        return user;
    }

    public User update(User user) {
        List<UserOrganizationJob> localUserOrganizationJobs = user.getOrganizationJobs();
        for (int i = 0, l = localUserOrganizationJobs.size(); i < l; i++) {

            UserOrganizationJob localUserOrganizationJob = localUserOrganizationJobs.get(i);
            //设置关系 防止丢失 报 A collection with cascade="all-delete-orphan" was no longer referenced by the owning entity instance
            localUserOrganizationJob.setUser(user);

            UserOrganizationJob dbUserOrganizationJob = findUserOrganizationJob(localUserOrganizationJob);
            if (dbUserOrganizationJob != null) {//出现在先删除再添加的情况
                dbUserOrganizationJob.setJobId(localUserOrganizationJob.getJobId());
                dbUserOrganizationJob.setOrganizationId(localUserOrganizationJob.getOrganizationId());
                dbUserOrganizationJob.setUser(localUserOrganizationJob.getUser());
                localUserOrganizationJobs.set(i, dbUserOrganizationJob);
            }
        }
        return user;
    }

    public UserOrganizationJob findUserOrganizationJob(UserOrganizationJob userOrganizationJob) {
        return null;/*getUserRepository().findUserOrganization(
                userOrganizationJob.getUser(),
                userOrganizationJob.getOrganizationId(),
                userOrganizationJob.getJobId());*/
    }

    public User findByUsername(String username) {
        if(StringUtils.isEmpty(username)) {
            return null;
        }
        return getLoginUser(username, USERNAME_TYPE);
    }

    public User findByEmail(String email) {
        if(StringUtils.isEmpty(email)) {
            return null;
        }
        return getLoginUser(email, EMAIL_TYPE);
    }

    public User findByMobilePhoneNumber(String mobilePhoneNumber) {
        if(StringUtils.isEmpty(mobilePhoneNumber)) {
            return null;
        }
        return getLoginUser(mobilePhoneNumber, PHONE_TYPE);
    }

    public User changePassword(User user, String newPassword) {
    	PasswordHelper aPasswordHelper = new PasswordHelper();
    	user.setPassword(newPassword);
    	aPasswordHelper.encryptPassword(user);
        update(user);
        return user;
    }

    public User changeStatus(User opUser, User user, UserStatus newStatus, String reason) {
        user.setStatus(newStatus);
        update(user);
        // userStatusHistoryService.log(opUser, user, newStatus, reason);
        return user;
    }

    public User login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            UserLogUtils.log(username, "loginError", "username is empty");
            throw new UserNotExistsException();
        }
        System.out.println("wwwwwwwwwwwwwww   into userservice login");
        
        //密码如果不在指定范围内 肯定错误
        if (password.length() < User.PASSWORD_MIN_LENGTH || password.length() > User.PASSWORD_MAX_LENGTH) {
            UserLogUtils.log(username, "loginError", "password length error! password is between {} and {}", User.PASSWORD_MIN_LENGTH, User.PASSWORD_MAX_LENGTH);
            throw new UserPasswordNotMatchException();
        }
        
        User user = null;
        if (maybeUsername(username)) {
        	user = getLoginUser(username, USERNAME_TYPE);
        }
        if (user == null && maybeEmail(username)) {
        	user = getLoginUser(username, EMAIL_TYPE);
        }
        if (user == null && maybeMobilePhoneNumber(username)) {
        	user = getLoginUser(username, PHONE_TYPE);
        }

        if (user == null || Boolean.TRUE.equals(user.getDeleted())) {
            UserLogUtils.log(username, "loginError", "user is not exists!");
            throw new UserNotExistsException();
        }

        passwordService.validate(user, password);

        if (user.getStatus() == UserStatus.blocked) {
            UserLogUtils.log(username, "loginError", "user is blocked!");
            throw new UserBlockedException("is not have content.");
            // throw new UserBlockedException(userStatusHistoryService.getLastReason(user));
        }

        UserLogUtils.log(username, "loginSuccess", "");
        return user;
    }

    /**
     * 通过登录时传入的用户名去依次匹配用户名，email，手机号码
     * @param userName
     * @param type
     * @return
     */
    private User getLoginUser(String userName, Integer type) {
    	User loginUser = new User();
    	String columnName = "|username|";
    	if (type == USERNAME_TYPE) {
    		columnName = "|username|";
    		loginUser.setUsername(userName);
    	} else if (type == EMAIL_TYPE) {
    		columnName = "|email|";
    		loginUser.setEmail(userName);
    	} else if (type == PHONE_TYPE) {
    		columnName = "|mobilePhoneNumber|";
    		loginUser.setMobilePhoneNumber(userName);
    	}
    	SqlHelper aSqlHelper = new SqlHelper(loginUser, "sys_user");
    	String sqlStr = aSqlHelper.getQuerySQLAll(columnName, "", "");
    	List<User> list = jdbcTemplate.query(sqlStr, aSqlHelper.getParams(), new BeanPropertyRowMapper<User>(User.class));
    	
    	if (null != list && list.size() == 1) {
    		return list.get(0);
    	} else {
    		return null;
    	}
    }

    private boolean maybeUsername(String username) {
        if (!username.matches(User.USERNAME_PATTERN)) {
            return false;
        }
        //如果用户名不在指定范围内也是错误的
        if (username.length() < User.USERNAME_MIN_LENGTH || username.length() > User.USERNAME_MAX_LENGTH) {
            return false;
        }

        return true;
    }

    private boolean maybeEmail(String username) {
        if (!username.matches(User.EMAIL_PATTERN)) {
            return false;
        }
        return true;
    }

    private boolean maybeMobilePhoneNumber(String username) {
        if (!username.matches(User.MOBILE_PHONE_NUMBER_PATTERN)) {
            return false;
        }
        return true;
    }

    public void changePassword(User opUser, Long[] ids, String newPassword) {
        UserService proxyUserService = (UserService) AopContext.currentProxy();
        /*for (Long id : ids) {
            User user = findOne(id);
            proxyUserService.changePassword(user, newPassword);
            UserLogUtils.log(
                    user.getUsername(),
                    "changePassword",
                    "admin user {} change password!", opUser.getUsername());

        }*/
    }

    public void changeStatus(User opUser, Long[] ids, UserStatus newStatus, String reason) {
        UserService proxyUserService = (UserService) AopContext.currentProxy();
        /*for (Long id : ids) {
            User user = findOne(id);
            proxyUserService.changeStatus(opUser, user, newStatus, reason);
            UserLogUtils.log(
                    user.getUsername(),
                    "changeStatus",
                    "admin user {} change status!", opUser.getUsername());
        }*/
    }

    public Set<Map<String, Object>> findIdAndNames(Searchable searchable, String usernme) {
        //searchable.addSearchFilter("username", SearchOperator.like, usernme);
        //searchable.addSearchFilter("deleted", SearchOperator.eq, false);

        return null;/*Sets.newHashSet(
                Lists.transform(
                        findAll(searchable).getContent(),
                        new Function<User, Map<String, Object>>() {
                            @Override
                            public Map<String, Object> apply(User input) {
                                Map<String, Object> data = Maps.newHashMap();
                                data.put("label", input.getUsername());
                                data.put("value", input.getId());
                                return data;
                            }
                        }
                )
        );*/
    }

    /**
     * 获取那些在用户-组织机构/工作职务中存在 但在组织机构/工作职务中不存在的
     *
     * @param pageable
     * @return
     */
    public List<UserOrganizationJob> findUserOrganizationJobOnNotExistsOrganizationOrJob() {
        return null;// getUserRepository().findUserOrganizationJobOnNotExistsOrganizationOrJob(pageable);
    }

    /**
     * 删除用户不存在的情况的UserOrganizationJob（比如手工从数据库物理删除）。。
     *
     * @return
     */
    public void deleteUserOrganizationJobOnNotExistsUser() {
        //getUserRepository().deleteUserOrganizationJobOnNotExistsUser();
    }
    
}
