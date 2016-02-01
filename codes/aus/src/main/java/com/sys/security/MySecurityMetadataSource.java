package com.sys.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import com.sys.model.vo.SecurityAuthorityVo;
import com.sys.service.AccountServiceI;
import com.sys.service.SecurityAuthorityServiceI;

/**
 * @description 资源源数据定义，将所有的资源和权限对应关系建立起来，即定义某一资源可以被哪些角色访问
 * @author yuxinchen
 * 
 */
public class MySecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	private AccountServiceI baseUserService;
	private SecurityAuthorityServiceI securityAuthorityService;
	/* 保存资源和权限的对应关系 key-资源url value-权限 */
	private Map<String, Collection<ConfigAttribute>> resourceMap = null;
	private AntPathMatcher urlMatcher = new AntPathMatcher();

	public MySecurityMetadataSource(
			SecurityAuthorityServiceI securityAuthorityService) {
		this.securityAuthorityService = securityAuthorityService;
		loadResourcesDefine();
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	private void loadResourcesDefine() {

		try {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

			List<SecurityAuthorityVo> authorityList = securityAuthorityService
					.findAuthorities();
			for (SecurityAuthorityVo securityAuthorityVo : authorityList) {
				Collection<ConfigAttribute> configAttributes = null;
				ConfigAttribute configAttribute = new SecurityConfig(
						securityAuthorityVo.getAuthName());
				if (resourceMap.containsKey(securityAuthorityVo.getUrl())) {
					configAttributes = resourceMap.get(securityAuthorityVo
							.getUrl());
					configAttributes.add(configAttribute);
				} else {
					configAttributes = new ArrayList<ConfigAttribute>();
					configAttributes.add(configAttribute);
					resourceMap.put(securityAuthorityVo.getUrl(),
							configAttributes);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 根据请求的资源地址，获取它所拥有的权限
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object obj)
			throws IllegalArgumentException {
		// 获取请求的url地址
		String url = ((FilterInvocation) obj).getRequestUrl();
		url = url.substring(1);
	
		Iterator<String> it = resourceMap.keySet().iterator();
		while (it.hasNext()) {
			String _url = it.next();
//			if (_url.indexOf("?") != -1) {
//				_url = _url.substring(0, _url.indexOf("?"));
				
//			}

			if (urlMatcher.match(_url, url))
				return resourceMap.get(url);
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

	public AccountServiceI getBaseUserService() {
		return baseUserService;
	}

	public void setBaseUserService(AccountServiceI baseUserService) {
		this.baseUserService = baseUserService;
	}

	public SecurityAuthorityServiceI getSecurityAuthorityService() {
		return securityAuthorityService;
	}

	public void setSecurityAuthorityService(
			SecurityAuthorityServiceI securityAuthorityService) {
		this.securityAuthorityService = securityAuthorityService;
	}

}