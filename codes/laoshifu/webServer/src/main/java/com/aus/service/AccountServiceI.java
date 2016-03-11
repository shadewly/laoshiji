package com.aus.service;

import com.aus.model.Account;

public interface AccountServiceI {
	/**
	 * 登录
	 */
	public boolean login(Account account);

	/**
	 * 注册
	 */
	public void register(Account account) throws Exception;

}
