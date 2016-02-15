package com.bz.service;

import java.util.List;
import java.util.Map;

import com.bz.model.Role;

public interface RoleServiceI {
	
	
	public List<Role> selectRoles(Map<String,Object> paraMap) throws Exception;

}
