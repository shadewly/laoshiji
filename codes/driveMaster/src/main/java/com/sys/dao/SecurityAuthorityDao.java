package com.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.mybatis.SqlMapper;
import com.sys.model.Account;

@Repository
public interface SecurityAuthorityDao extends SqlMapper {

	/**
	 * 查询所有menu
	 * 
	 * @return
	 */
	public Account selectAccount();

	public Object selectBySql();

	public List selectSecurityAuthorityList();
}
