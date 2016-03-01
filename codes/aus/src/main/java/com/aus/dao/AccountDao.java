package com.aus.dao;

import com.aus.model.Account;
import com.common.mybatis.SqlMapper;

public interface AccountDao extends SqlMapper {

	public Account selectAccount();
}
