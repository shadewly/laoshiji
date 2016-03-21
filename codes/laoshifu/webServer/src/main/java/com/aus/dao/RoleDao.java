package com.aus.dao;

import java.util.List;
import java.util.Map;

import com.aus.model.Role;
import com.wb.common.mybatis.SqlMapper;

public interface RoleDao extends SqlMapper {

	

	public List<Role> selectRoleList(Map<String,Object> paraMap);
}
