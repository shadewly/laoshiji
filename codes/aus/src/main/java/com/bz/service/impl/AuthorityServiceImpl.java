package com.bz.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bz.dao.AuthorityDao;
import com.bz.model.Authority;
import com.bz.service.AuthorityServiceI;

@Service(value = "authorityService")
@Transactional
public class AuthorityServiceImpl implements AuthorityServiceI {
	@Autowired
	@Qualifier("authorityDao")
	private AuthorityDao authorityDao;

	public AuthorityDao getAuthorityDao() {
		return authorityDao;
	}

	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}

	@Override
	public List<Authority> selectAuthorityList(Map<String,Object> paraMap) throws Exception {

		return authorityDao.selectAuthorityList(paraMap);
	}

}
