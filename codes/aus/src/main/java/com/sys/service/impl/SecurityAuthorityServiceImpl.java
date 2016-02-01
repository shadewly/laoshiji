package com.sys.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.util.ReflectionUtil;
import com.sys.dao.SecurityAuthorityDao;
import com.sys.model.SecurityAuthority;
import com.sys.model.vo.SecurityAuthorityVo;
import com.sys.service.SecurityAuthorityServiceI;

@Service(value = "securityAuthorityService")
@Transactional
public class SecurityAuthorityServiceImpl implements SecurityAuthorityServiceI {
	@Autowired
	@Qualifier("securityAuthorityDao")
	private SecurityAuthorityDao securityAuthorityDao;

	@Override
	public List<SecurityAuthority> findAuthoritiesByRoleCode(String roleCode)
			throws Exception {
		StringBuilder sql = new StringBuilder(
				"select * from t_security_role t1 left join t_security_role_authority t2 on t1.ROLE_CODE=t2.ROLE_CODE left join t_security_authority t3 on t2.AUTH_CODE=t3.AUTH_CODE where t1.ROLE_CODE='");
		sql.append(roleCode);
		sql.append("'");
//		return securityAuthorityDao.selectBySql(SecurityAuthority.class,
//				sql.toString());
		return null;

	}

	public SecurityAuthorityDao getsecurityAuthorityDao() {
		return securityAuthorityDao;
	}

	public void setsecurityAuthorityDao(
			SecurityAuthorityDao securityAuthorityDao) {
		this.securityAuthorityDao = securityAuthorityDao;
	}

	@Override
	public List<SecurityAuthorityVo> findAuthorities() throws Exception {

		List modelList = null;
		List resultMapList = securityAuthorityDao.selectSecurityAuthorityList();

		if (null != resultMapList && resultMapList.size() > 0) {
			modelList = ReflectionUtil.createObjListByClass(SecurityAuthorityVo.class, resultMapList);
		}

		return modelList;
	}

	@Override
	public Map<String, Object> updateAuthority(SecurityAuthority authority)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap = updateData(SecurityAuthority.class, authority, "authId");
//		return resultMap;
		return null;
	}

	@Override
	public Map<String, Object> addAuthority(SecurityAuthority authority)
			throws Exception {
		int maxAuthorityCode = 0;
//		securityAuthorityDao.selectMaxValue(
//				"t_security_authority", "AUTH_CODE");
		int newAuthorityCode = maxAuthorityCode + 1;
		authority.setAuthCode(String.valueOf(newAuthorityCode));
//		return addData(SecurityAuthority.class, authority);
		return null;
	}

	@Override
	public Map<String, Object> delAuthority(String selectedIds)
			throws Exception {

//		return super.delData(SecurityAuthority.class, selectedIds);
		return null;
	}

//	@Override
//	public Map<String, Object> getUpdateAuthorityItems(String paramData,
//			ControlItemVo controlData) throws Exception {
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//
//		StringBuffer sql = new StringBuffer(
//				"select T1.AUTH_ID,T1.AUTH_NAME,T1.AUTH_CODE  from t_security_authority T1 where T1.AUTH_ID=");
//		sql.append(paramData.split("_")[1]);
//
//		SecurityAuthority authority = (SecurityAuthority) securityAuthorityDao
//				.selectObjectBySql(SecurityAuthority.class, sql.toString());
//
//		resultMap.put("authority", authority);
//
//		return resultMap;
//	}

}
