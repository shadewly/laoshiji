package com.aus.dao;

import java.util.List;
import java.util.Map;

import com.aus.model.Authority;
import com.common.mybatis.SqlMapper;

public interface AuthorityDao extends SqlMapper {

	

	public List<Authority> selectAuthorityList(Map<String,Object> paraMap);
}
