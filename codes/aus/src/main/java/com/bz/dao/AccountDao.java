package com.bz.dao;

import com.bz.model.Account;
import com.core.mybatis.SqlMapper;

public interface AccountDao extends SqlMapper {

	public Account selectAccount();
}
