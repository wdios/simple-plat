package com.wis.exception.user;

/**
 * 密码错误重试达到最大次数，提示用户。
 */
public class UserPasswordRetryLimitMaxException extends UserException {
	private static final long serialVersionUID = 1L;

	public UserPasswordRetryLimitMaxException(int retryLimitCount) {
        super("user.password.retry.limit.max", new Object[]{retryLimitCount});
    }
}
