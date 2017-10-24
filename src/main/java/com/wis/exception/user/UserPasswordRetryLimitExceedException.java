package com.wis.exception.user;

/**
 * 密码错误重试超过次数，5 次，锁定 10 分钟。
 */
public class UserPasswordRetryLimitExceedException extends UserException {
	private static final long serialVersionUID = 1L;

	public UserPasswordRetryLimitExceedException(int retryLimitCount) {
        super("user.password.retry.limit.exceed", new Object[]{retryLimitCount});
    }
}
