package com.wis.exception.user;

import com.wis.common.BaseException;

/**
 * 系统用户异常
 */
public class UserException extends BaseException {
	private static final long serialVersionUID = 1L;

	public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }

}
