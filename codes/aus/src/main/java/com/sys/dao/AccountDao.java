package com.sys.dao;

import com.core.mybatis.SqlMapper;
import com.sys.model.Account;

public interface  AccountDao extends SqlMapper {
	
	/**
	 * 查询所有menu
	 * @return
	 */
	public Account selectAccount();
}
