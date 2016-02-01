package com.sys.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.mybatis.CustomerContextHolder;
import com.sys.dao.AccountDao;
import com.sys.model.Account;
import com.sys.model.SecurityAuthority;
import com.sys.model.vo.SecurityUserRoleVo;
import com.sys.service.AccountServiceI;
import com.sys.service.SecurityAuthorityServiceI;

/**
 * 基础user服务类
 * 
 * @author yuxinchen
 * 
 */
@Service(value = "accountService")
@Transactional
public class AccountServiceImpl implements AccountServiceI , UserDetailsService {
	@Autowired
	private AccountDao accountDao;
//	@Autowired
//	UrlServiceI urlService;
	@Autowired
	SecurityAuthorityServiceI securityAuthorityService;

	// 以下是spring security內容
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Account account = null;
		boolean enabled = true; // 是否可用
		boolean accountNonExpired = true; // 是否过期
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		try {
			// 通过该方式设定数据源
			CustomerContextHolder
					.setContextType(CustomerContextHolder.SESSION_FACTORY_DRIVE_MASTER);
			account = null;
//			(Account) accountDao.selectObjectBySql(
//					Account.class,
//					"select * from t_base_user where user_name='" + username
//							+ "'");

			// 通过角色获取权限
			List<SecurityUserRoleVo> roles = findUserRolesByUserName(username);

			if (null != roles) {
				for (SecurityUserRoleVo role : roles) {
					List<SecurityAuthority> securityAuthorityList = securityAuthorityService
							.findAuthoritiesByRoleCode(role.getRoleCode());
					for (SecurityAuthority securityAuthority : securityAuthorityList) {
						GrantedAuthority ga = new SimpleGrantedAuthority(
								securityAuthority.getAuthName());
						authorities.add(ga);
					}

				}
			}

		} catch (Exception e) {
//			log.error(SysConstant.ERROR_MSG, e);
			e.printStackTrace();
		}
		return new org.springframework.security.core.userdetails.User(
				account.getUserName(), account.getUserPwd(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);

	}

	public List<SecurityUserRoleVo> findUserRolesByUserName(String username)
			throws Exception {
		StringBuilder sql = new StringBuilder(
				"select t1.USER_NAME,t2.ROLE_NAME,t2.ROLE_CODE from t_security_role_user t1 left join t_security_role t2 on t1.ROLE_CODE=t2.ROLE_CODE where user_name='");
		sql.append(username);
		sql.append("'");
//		return accountDao
//				.selectBySql(SecurityUserRoleVo.class, sql.toString());
		return null;

	}


	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public Map<String, Object> updateBaseUser(Account baseUser)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> addBaseUser(Account baseUser) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> delBaseUser(String selectedIds) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Map<String, Object> getUpdateBaseUserItems(String paramData,
//			ControlItemVo controlData) throws Exception {
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//
//		StringBuffer sql = new StringBuffer(
//				"select T1.USER_ID,T1.USER_NAME,T1.USER_PWD from t_base_user T1 where T1.USER_ID=");
//		sql.append(paramData.split("_")[1]);
//
//		TBaseUser user = (TBaseUser) baseUserDao.selectObjectBySql(
//				TBaseUser.class, sql.toString());
//
//		resultMap.put("user", user);
//
//		return resultMap;
//	}

//	@Override
//	public Map<String, Object> updateBaseUser(TBaseUser baseUser)
//			throws Exception {
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap = updateData(TBaseUser.class, baseUser, "userId");
//		return resultMap;
//	}

//	@Override
//	public Map<String, Object> addBaseUser(TBaseUser baseUser) throws Exception {
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		TBaseUser user = (TBaseUser) baseUserDao.selectObjectBySql(
//				TBaseUser.class, "select * from t_base_user where user_name='"
//						+ baseUser.getUserName() + "'");
//		if (null == user) {
//			baseUser.setUserPwd(EncryptUtil.md5(baseUser.getUserPwd()));
//			resultMap = addData(TBaseUser.class, baseUser);
//		} else {
//			resultMap.put(SysConstant.JsonResult.Result.getIndex(),
//					SysConstant.JsonResult.Fail.getIndex());
//			resultMap.put(SysConstant.JsonResult.Msg.getIndex(), "用户名已经存在！");
//		}
//		return resultMap;
//	}

//	@Override
//	public Map<String, Object> delBaseUser(String selectedIds) throws Exception {
//
//		return super.delData(TBaseUser.class, selectedIds);
//	}

}
