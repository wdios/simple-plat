package com.wis.exception.user;

/**
 * 用户密码错误
 */
public class UserPasswordNotMatchException extends UserException {
	private static final long serialVersionUID = 1L;

	public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
