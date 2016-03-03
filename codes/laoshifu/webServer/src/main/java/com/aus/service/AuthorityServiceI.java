package com.aus.service;

import java.util.List;
import java.util.Map;

import com.aus.model.Authority;

public interface AuthorityServiceI {

	
	public List<Authority> selectAuthorityList(Map<String, Object> paraMap)
			throws Exception;

}
