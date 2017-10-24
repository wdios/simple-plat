/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.apache.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.wis.exception.user.UserBlockedException;
import com.wis.exception.user.UserNotExistsException;
import com.wis.exception.user.UserPasswordNotMatchException;
import com.wis.exception.user.UserPasswordRetryLimitExceedException;
import com.wis.exception.user.UserPasswordRetryLimitMaxException;
import com.wis.model.user.User;
import com.wis.service.UserAuthService;
import com.wis.service.UserService;

/**
 * 用户登录认证
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthService userAuthService;

    private static final Logger log = LoggerFactory.getLogger("es-error");

    @Autowired
    public UserRealm(ApplicationContext ctx) {
        super();
        // 不能注入，因为获取 bean 依赖顺序问题造成可能拿不到某些bean报错
        // why？
        // 因为 spring 在查找 findAutowireCandidates 时对 FactoryBean 做了优化，即只获取 Bean，但不会 autowire 属性，
        // 所以如果我们的 bean 在依赖它的 bean 之前初始化，那么就得不到 ObjectType（永远是Repository）
        // 所以此处我们先 getBean 一下，就没有问题了
        // ctx.getBeansOfType(SimpleBaseRepositoryFactoryBean.class);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        User user = userService.findByUsername(username);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // authorizationInfo.setRoles(userAuthService.findStringRoles(user));
        // authorizationInfo.setStringPermissions(userAuthService.findStringPermissions(user));

        return authorizationInfo;
    }

    private static final String OR_OPERATOR = " or ";
    private static final String AND_OPERATOR = " and ";
    private static final String NOT_OPERATOR = "not ";

    /**
     * 支持or and not 关键词  不支持and or混用
     *
     * @param principals
     * @param permission
     * @return
     */
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        if (permission.contains(OR_OPERATOR)) {
            String[] permissions = permission.split(OR_OPERATOR);
            for (String orPermission : permissions) {
                if (isPermittedWithNotOperator(principals, orPermission)) {
                    return true;
                }
            }
            return false;
        } else if (permission.contains(AND_OPERATOR)) {
            String[] permissions = permission.split(AND_OPERATOR);
            for (String orPermission : permissions) {
                if (!isPermittedWithNotOperator(principals, orPermission)) {
                    return false;
                }
            }
            return true;
        } else {
            return isPermittedWithNotOperator(principals, permission);
        }
    }

    private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
        if (permission.startsWith(NOT_OPERATOR)) {
            return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
        } else {
            return super.isPermitted(principals, permission);
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	System.out.println("wwwwwwwwwwwww  in the UserRealm");
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        
        String username = "";
        if (null != upToken.getUsername()) {
        	username = upToken.getUsername().trim();
        }
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }

        User user = null;
        try {
            user = userService.login(username, password);
        } catch (UserNotExistsException e) {
            throw new UnknownAccountException("UserNotExistsException", e);
        } catch (UserPasswordNotMatchException e) {
            throw new AuthenticationException("UserPasswordNotMatchException", e);
        } catch (UserPasswordRetryLimitMaxException e) {
        	throw new ExcessiveAttemptsException("UserPasswordRetryLimitMaxException", e);
        } catch (UserPasswordRetryLimitExceedException e) {
            throw new ExcessiveAttemptsException("UserPasswordRetryLimitExceedException", e);
        } catch (UserBlockedException e) {
            throw new LockedAccountException("UserBlockedException", e);
        } catch (Exception e) {
            throw new AuthenticationException("UnknownException", e);
        }

        // System.out.println("wwwwwwwwwwwww  user.getUsername()=" + user.getUsername() + "|||user.getPassword()=" + user.getPassword() + "|||user.getCredentialsSalt()=" + user.getCredentialsSalt());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        		username, // 用户名
                password, // 密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),	// salt=username+salt
                getName()  // realm name
        );

        return authenticationInfo;
    }

}
