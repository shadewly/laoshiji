package com.bz.dao;

import java.util.List;
import java.util.Map;

import com.bz.model.Authority;
import com.core.mybatis.SqlMapper;

public interface AuthorityDao extends SqlMapper {

	

	public List<Authority> selectAuthorityList(Map<String,Object> paraMap);
}
