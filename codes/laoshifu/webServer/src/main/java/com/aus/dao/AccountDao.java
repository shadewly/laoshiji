package com.aus.dao;

import com.aus.model.Account;
import com.wb.common.mybatis.SqlMapper;

public interface AccountDao extends SqlMapper {

	/**
	 * 查询账号
	 * @return
	 */
	public Account selectAccount();
	
	/**
	 * 插入账号
	 * @return
	 */
	public int insertAccount(Account account);
	
	/**
	 * 统计账号账号
	 * @return
	 */
	public int countAccount(Account account);
}
