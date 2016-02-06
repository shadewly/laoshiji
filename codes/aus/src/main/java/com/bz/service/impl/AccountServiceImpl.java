package com.bz.service.impl;

import java.util.HashMap;
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

import com.bz.dao.AccountDao;
import com.bz.model.Account;
import com.bz.model.Authority;
import com.bz.model.Role;
import com.bz.service.AccountServiceI;
import com.bz.service.AuthorityServiceI;
import com.bz.service.RoleServiceI;
import com.core.mybatis.CustomerContextHolder;

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
	AuthorityServiceI authorityService;
	@Autowired
	RoleServiceI roleService;

	// 浠ヤ笅鏄痵pring security鍏у
	@Override
	public UserDetails loadUserByUsername(String accountNo)
			throws UsernameNotFoundException {
		Account account = null;
		boolean enabled = true; // 鏄惁鍙敤
		boolean accountNonExpired = true; // 鏄惁杩囨湡
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		try {
			// ****** 账号需要cas端返回，角色集合需要启动时加载中获取
			CustomerContextHolder
					.setContextType(CustomerContextHolder.SESSION_FACTORY_AUS);
			account = new Account();
			account.setAccountNo("yxc");
			account.setPassword("123");

			// 閫氳繃瑙掕壊鑾峰彇鏉冮檺
			Map<String, Object> roleParaMap = new HashMap<String, Object>();
			roleParaMap.put("accountNo", accountNo);
			List<Role> roles = roleService.selectRoles(roleParaMap);

			if (null != roles && roles.size() > 0) {
				for (Role role : roles) {
					Map<String, Object> paraMap = new HashMap<String, Object>();
					paraMap.put("roleCode", role.getRoleCode());
					List<Authority> authorityList = authorityService
							.selectAuthorityList(paraMap);
					if (authorityList != null && authorityList.size() > 0) {
						for (Authority authority : authorityList) {
							GrantedAuthority ga = new SimpleGrantedAuthority(
									authority.getAuthName());
							authorities.add(ga);
						}
					}

				}
			}

		} catch (Exception e) {
			// log.error(SysConstant.ERROR_MSG, e);
			e.printStackTrace();
		}
		return new org.springframework.security.core.userdetails.User(
				account.getAccountNo(), account.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);
		// Collection<GrantedAuthority> auths = new
		// ArrayList<GrantedAuthority>();
		// UserDetails userDetails = new UserDetails() {
		// /**
		// * Returns the authorities granted to the user. Cannot return
		// * <code>null</code>.
		// *
		// * @return the authorities, sorted by natural key (never
		// * <code>null</code>)
		// */
		// @Override
		// public Collection<? extends GrantedAuthority> getAuthorities() {
		// SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
		// "ROLE_USER");
		// auths.add(authority);
		//
		// return auths;
		// }
		//
		// /**
		// * Returns the password used to authenticate the user.
		// *
		// * @return the password
		// */
		// @Override
		// public String getPassword() {
		// return "123";
		// }
		//
		// /**
		// * Returns the username used to authenticate the user. Cannot return
		// * <code>null</code> .
		// *
		// * @return the username (never <code>null</code>)
		// */
		// @Override
		// public String getUsername() {
		// return "yxc";
		// }
		//
		// /**
		// * Indicates whether the user's account has expired. An expired
		// * account cannot be authenticated.
		// *
		// * @return <code>true</code> if the user's account is valid (ie
		// * non-expired), <code>false</code> if no longer valid (ie
		// * expired)
		// */
		// @Override
		// public boolean isAccountNonExpired() {
		// return true;
		// }
		//
		// /**
		// * Indicates whether the user is locked or unlocked. A locked user
		// * cannot be authenticated.
		// *
		// * @return <code>true</code> if the user is not locked,
		// * <code>false</code> otherwise
		// */
		// @Override
		// public boolean isAccountNonLocked() {
		// return true;
		// }
		//
		// /**
		// * Indicates whether the user's credentials (password) has expired.
		// * Expired credentials prevent authentication.
		// *
		// * @return <code>true</code> if the user's credentials are valid (ie
		// * non-expired), <code>false</code> if no longer valid (ie
		// * expired)
		// */
		// @Override
		// public boolean isCredentialsNonExpired() {
		// return true;
		// }
		//
		// /**
		// * Indicates whether the user is enabled or disabled. A disabled
		// * user cannot be authenticated.
		// *
		// * @return <code>true</code> if the user is enabled,
		// * <code>false</code> otherwise
		// */
		// @Override
		// public boolean isEnabled() {
		// return true;
		// }
		// };
		// return userDetails;

	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

}
