package com.sys.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.dao.AccountDao;
import com.sys.model.Account;
import com.sys.model.vo.SecurityUserRoleVo;
import com.sys.service.AccountServiceI;
import com.sys.service.SecurityAuthorityServiceI;

/**
 * 鍩虹user鏈嶅姟绫�
 * 
 * @author yuxinchen
 * 
 */
@Service(value = "accountService")
@Transactional
public class AccountServiceImpl implements AccountServiceI, UserDetailsService {
	@Autowired
	private AccountDao accountDao;
	// @Autowired
	// UrlServiceI urlService;
	@Autowired
	SecurityAuthorityServiceI securityAuthorityService;

	// 浠ヤ笅鏄痵pring security鍏у
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// Account account = null;
		// boolean enabled = true; // 鏄惁鍙敤
		// boolean accountNonExpired = true; // 鏄惁杩囨湡
		// boolean credentialsNonExpired = true;
		// boolean accountNonLocked = true;
		// Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		// try {
		// // 閫氳繃璇ユ柟寮忚瀹氭暟鎹簮
		// CustomerContextHolder
		// .setContextType(CustomerContextHolder.SESSION_FACTORY_AUS);
		// account = null;
		// // (Account) accountDao.selectObjectBySql(
		// // Account.class,
		// // "select * from t_base_user where user_name='" + username
		// // + "'");
		//
		// // 閫氳繃瑙掕壊鑾峰彇鏉冮檺
		// List<SecurityUserRoleVo> roles = findUserRolesByUserName(username);
		//
		// if (null != roles) {
		// for (SecurityUserRoleVo role : roles) {
		// List<SecurityAuthority> securityAuthorityList =
		// securityAuthorityService
		// .findAuthoritiesByRoleCode(role.getRoleCode());
		// for (SecurityAuthority securityAuthority : securityAuthorityList) {
		// GrantedAuthority ga = new SimpleGrantedAuthority(
		// securityAuthority.getAuthName());
		// authorities.add(ga);
		// }
		//
		// }
		// }
		//
		// } catch (Exception e) {
		// // log.error(SysConstant.ERROR_MSG, e);
		// e.printStackTrace();
		// }
		// return new org.springframework.security.core.userdetails.User(
		// account.getUserName(), account.getUserPwd(), enabled,
		// accountNonExpired, credentialsNonExpired, accountNonLocked,
		// authorities);
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		UserDetails userDetails = new UserDetails() {
			/**
			 * Returns the authorities granted to the user. Cannot return
			 * <code>null</code>.
			 * 
			 * @return the authorities, sorted by natural key (never
			 *         <code>null</code>)
			 */
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
						"ROLE_USER");
				auths.add(authority);

				return auths;
			}

			/**
			 * Returns the password used to authenticate the user.
			 * 
			 * @return the password
			 */
			@Override
			public String getPassword() {
				return "123";
			}

			/**
			 * Returns the username used to authenticate the user. Cannot return
			 * <code>null</code> .
			 * 
			 * @return the username (never <code>null</code>)
			 */
			@Override
			public String getUsername() {
				return "yxc";
			}

			/**
			 * Indicates whether the user's account has expired. An expired
			 * account cannot be authenticated.
			 * 
			 * @return <code>true</code> if the user's account is valid (ie
			 *         non-expired), <code>false</code> if no longer valid (ie
			 *         expired)
			 */
			@Override
			public boolean isAccountNonExpired() {
				return true;
			}

			/**
			 * Indicates whether the user is locked or unlocked. A locked user
			 * cannot be authenticated.
			 * 
			 * @return <code>true</code> if the user is not locked,
			 *         <code>false</code> otherwise
			 */
			@Override
			public boolean isAccountNonLocked() {
				return true;
			}

			/**
			 * Indicates whether the user's credentials (password) has expired.
			 * Expired credentials prevent authentication.
			 * 
			 * @return <code>true</code> if the user's credentials are valid (ie
			 *         non-expired), <code>false</code> if no longer valid (ie
			 *         expired)
			 */
			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}

			/**
			 * Indicates whether the user is enabled or disabled. A disabled
			 * user cannot be authenticated.
			 * 
			 * @return <code>true</code> if the user is enabled,
			 *         <code>false</code> otherwise
			 */
			@Override
			public boolean isEnabled() {
				return true;
			}
		};
		return userDetails;

	}

	public List<SecurityUserRoleVo> findUserRolesByUserName(String username)
			throws Exception {
		StringBuilder sql = new StringBuilder(
				"select t1.USER_NAME,t2.ROLE_NAME,t2.ROLE_CODE from t_security_role_user t1 left join t_security_role t2 on t1.ROLE_CODE=t2.ROLE_CODE where user_name='");
		sql.append(username);
		sql.append("'");
		// return accountDao
		// .selectBySql(SecurityUserRoleVo.class, sql.toString());
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

	// @Override
	// public Map<String, Object> getUpdateBaseUserItems(String paramData,
	// ControlItemVo controlData) throws Exception {
	// Map<String, Object> resultMap = new HashMap<String, Object>();
	//
	// StringBuffer sql = new StringBuffer(
	// "select T1.USER_ID,T1.USER_NAME,T1.USER_PWD from t_base_user T1 where T1.USER_ID=");
	// sql.append(paramData.split("_")[1]);
	//
	// TBaseUser user = (TBaseUser) baseUserDao.selectObjectBySql(
	// TBaseUser.class, sql.toString());
	//
	// resultMap.put("user", user);
	//
	// return resultMap;
	// }

	// @Override
	// public Map<String, Object> updateBaseUser(TBaseUser baseUser)
	// throws Exception {
	// Map<String, Object> resultMap = new HashMap<String, Object>();
	// resultMap = updateData(TBaseUser.class, baseUser, "userId");
	// return resultMap;
	// }

	// @Override
	// public Map<String, Object> addBaseUser(TBaseUser baseUser) throws
	// Exception {
	// Map<String, Object> resultMap = new HashMap<String, Object>();
	// TBaseUser user = (TBaseUser) baseUserDao.selectObjectBySql(
	// TBaseUser.class, "select * from t_base_user where user_name='"
	// + baseUser.getUserName() + "'");
	// if (null == user) {
	// baseUser.setUserPwd(EncryptUtil.md5(baseUser.getUserPwd()));
	// resultMap = addData(TBaseUser.class, baseUser);
	// } else {
	// resultMap.put(SysConstant.JsonResult.Result.getIndex(),
	// SysConstant.JsonResult.Fail.getIndex());
	// resultMap.put(SysConstant.JsonResult.Msg.getIndex(), "鐢ㄦ埛鍚嶅凡缁忓瓨鍦紒");
	// }
	// return resultMap;
	// }

	// @Override
	// public Map<String, Object> delBaseUser(String selectedIds) throws
	// Exception {
	//
	// return super.delData(TBaseUser.class, selectedIds);
	// }

}
