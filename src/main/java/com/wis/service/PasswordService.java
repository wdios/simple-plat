package com.wis.service;

import javax.annotation.PostConstruct;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wis.exception.user.UserPasswordNotMatchException;
import com.wis.exception.user.UserPasswordRetryLimitExceedException;
import com.wis.exception.user.UserPasswordRetryLimitMaxException;
import com.wis.model.user.User;
import com.wis.tookit.PasswordHelper;
import com.wis.tookit.UserLogUtils;

/**
 * 用户密码相关操作
 */
@Service
public class PasswordService {

    @Autowired
    private CacheManager ehcacheManager;
    /**
     * 登录缓存
     */
    private Cache loginRecordCache;
    /**
     * 重试次数
     */
    private int maxRetryCount = 5;

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    @PostConstruct
    public void init() {
        loginRecordCache = ehcacheManager.getCache("loginRecordCache");
    }

    public void validate(User user, String password) {
        String username = user.getUsername();

        int retryCount = 0;
        
        System.out.println("wwwwwwwwwwwwww  into PasswordService validate.");
        Element cacheElement = loginRecordCache.get(username);
        if (null != cacheElement) {
        	retryCount = (Integer) cacheElement.getObjectValue();
        	if (retryCount > maxRetryCount) {
                UserLogUtils.log(
                        username,
                        "passwordError",
                        "password error, retry limit exceed! password: {},max retry count {}",
                        password, maxRetryCount);
                throw new UserPasswordRetryLimitExceedException(maxRetryCount);
            }
        	if (retryCount == maxRetryCount) {
                UserLogUtils.log(
                        username,
                        "passwordTip",
                        "password error, retry limit max! password: {},max retry count {}",
                        password, maxRetryCount);
                loginRecordCache.put(new Element(username, ++retryCount));
                throw new UserPasswordRetryLimitMaxException(maxRetryCount);
            }
        }

        if (!matches(user, password)) {
            loginRecordCache.put(new Element(username, ++retryCount));
            UserLogUtils.log(
                    username,
                    "passwordError",
                    "password error! password: {} retry count: {}",
                    password, retryCount);
            throw new UserPasswordNotMatchException();
        } else {
            clearLoginRecordCache(username);
        }
    }

    public boolean matches(User user, String newPassword) {
    	PasswordHelper aPasswordHelper = new PasswordHelper();
        return user.getPassword().equals(aPasswordHelper.getEncryptPassword(user.getUsername(), newPassword, user.getSalt()));
    }

    public void clearLoginRecordCache(String username) {
        loginRecordCache.remove(username);
    }

    public static void main(String[] args) {
    	PasswordHelper aPasswordHelper = new PasswordHelper();
        System.out.println(aPasswordHelper.getEncryptPassword("wd", "123111", "e7020fb0e2ee96e04cc7779205c410dc"));
    }
    
}
