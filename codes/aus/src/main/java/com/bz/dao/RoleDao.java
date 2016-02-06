package com.bz.dao;

import java.util.List;
import java.util.Map;

import com.bz.model.Role;
import com.core.mybatis.SqlMapper;

public interface RoleDao extends SqlMapper {

	

	public List<Role> selectRoleList(Map<String,Object> paraMap);
}
