package com.wis.exception.user;

/**
 * 用户不存在
 */
public class UserNotExistsException extends UserException {
	private static final long serialVersionUID = 1L;

	public UserNotExistsException() {
        super("user.not.exists", null);
        System.out.println("wwwwwwwwwwwww  in UserNotExistsException.");
    }
}
