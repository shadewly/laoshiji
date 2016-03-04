package com.sys.dao;

import com.core.mybatis.SqlMapper;
import com.sys.model.Account;

public interface  SecurityAuthorityDao extends SqlMapper {
	
	/**
	 * 查询所有menu
	 * @return
	 */
	public Account selectAccount();
	public Object selectBySql();
}
