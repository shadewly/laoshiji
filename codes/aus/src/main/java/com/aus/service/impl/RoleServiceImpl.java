package com.aus.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aus.dao.RoleDao;
import com.aus.model.Role;
import com.aus.service.RoleServiceI;

@Service(value = "roleService")
@Transactional
public class RoleServiceImpl implements RoleServiceI {
	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;

	@Override
	public List<Role> selectRoles(Map<String, Object> paraMap) throws Exception {

		return roleDao.selectRoleList(paraMap);
	}

}
