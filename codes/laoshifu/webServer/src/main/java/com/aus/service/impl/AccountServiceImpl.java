package com.aus.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aus.dao.AccountDao;
import com.aus.model.Account;
import com.aus.model.Authority;
import com.aus.model.Role;
import com.aus.security.core.CasRestClient;
import com.aus.service.AccountServiceI;
import com.aus.service.AuthorityServiceI;
import com.aus.service.RoleServiceI;
import com.common.mybatis.CustomerContextHolder;

/**
 * 鍩虹user鏈嶅姟绫�
 * 
 * @author yuxinchen
 * 
 */
@Service(value = "accountService")
@Transactional
public class AccountServiceImpl implements AccountServiceI, UserDetailsService/* AuthenticationUserDetailsService */{
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
		// Account account = null;
		boolean enabled = true; // 鏄惁鍙敤
		boolean accountNonExpired = true; // 鏄惁杩囨湡
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		try {
			// ****** 账号需要cas端返回，角色集合需要启动时加载中获取
			CustomerContextHolder
					.setContextType(CustomerContextHolder.SESSION_FACTORY_WEB_SERVER);
			// SecurityContextImpl securityContextImpl = (SecurityContextImpl)
			// request
			// .getSession().getAttribute("SPRING_SECURITY_CONTEXT");

			// UserDetails aa1=delegate.loadUserByUsername(accountNo);

			// account = (Account) SecurityContextHolder.getContext()
			// .getAuthentication().getPrincipal();
			// account = new Account();
			// account.setAccountNo("yxc");

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
				accountNo, "N/A", enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);

	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public boolean login(Account account) {
		String ticket = CasRestClient.getTicket(
				"https://localhost:8443/cas/v1/tickets",
				account.getAccountNo(), account.getPassword(),
				"https://www.baidu.com");
		return CasRestClient.ticketValidate(
				"https://localhost:8443/cas/proxyValidate", ticket,
				"https://www.baidu.com");

	}

}
