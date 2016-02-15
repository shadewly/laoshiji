package com.bz.service;

import java.util.List;
import java.util.Map;

import com.bz.model.Authority;

public interface AuthorityServiceI {

	
	public List<Authority> selectAuthorityList(Map<String, Object> paraMap)
			throws Exception;

}
