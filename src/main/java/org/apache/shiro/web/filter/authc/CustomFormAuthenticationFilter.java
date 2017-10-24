package org.apache.shiro.web.filter.authc;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.wis.model.user.User;
import com.wis.service.UserService;

/**
 * 基于几点修改：
 * 1、onLoginFailure 时 把异常添加到request attribute中 而不是异常类名
 * 2、登录成功时：成功页面重定向：
 * 2.1、如果前一个页面是登录页面，-->2.3
 * 2.2、如果有SavedRequest 则返回到SavedRequest
 * 2.3、否则根据当前登录的用户决定返回到管理员首页/前台首页
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    @Autowired
    UserService userService;

    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
    	request.setAttribute(getFailureKeyAttribute(), ae);
    }
    
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                //本次用户登陆账号
                String account = this.getUsername(request);
                Subject subject = this.getSubject(request, response);
                //之前登陆的用户
                String user = (String) subject.getPrincipal();
                //如果两次登陆的用户不一样，则先退出之前登陆的用户
                if (account != null && user != null && !account.equals(user)) {
                    subject.logout();
                }
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * 默认的成功地址
     */
    private String defaultSuccessUrl;
    /**
     * 管理员默认的成功地址
     */
    private String adminDefaultSuccessUrl;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setDefaultSuccessUrl(String defaultSuccessUrl) {
        this.defaultSuccessUrl = defaultSuccessUrl;
    }

    public void setAdminDefaultSuccessUrl(String adminDefaultSuccessUrl) {
        this.adminDefaultSuccessUrl = adminDefaultSuccessUrl;
    }

    public String getDefaultSuccessUrl() {
        return defaultSuccessUrl;
    }

    public String getAdminDefaultSuccessUrl() {
        return adminDefaultSuccessUrl;
    }

    /**
     * 根据用户选择成功地址
     */
    @Override
    public String getSuccessUrl() {
    	System.out.println("wwwwwwwwwwwwwwwwww into CustomFormAuthenticationFilter getSuccessUrl.");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.findByUsername(username);
        if (user != null && Boolean.TRUE.equals(user.getAdmin())) {
            return getAdminDefaultSuccessUrl();
        }
        return getDefaultSuccessUrl();
    }
    
}
