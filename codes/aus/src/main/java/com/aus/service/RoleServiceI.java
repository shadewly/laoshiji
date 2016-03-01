package com.aus.service;

import java.util.List;
import java.util.Map;

import com.aus.model.Role;

public interface RoleServiceI {
	
	
	public List<Role> selectRoles(Map<String,Object> paraMap) throws Exception;

}
