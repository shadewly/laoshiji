package com.aus.security.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * @description 一个自定义的filter，
 *              必须包含authenticationManager,accessDecisionManager,securityMetadataSource三个属性
 *              ， 我们的所有控制将在这三个类中实现
 * @author yuxinchen
 * 
 */
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor
		implements Filter {

	private FilterInvocationSecurityMetadataSource fisMetadataSource;

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return fisMetadataSource;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		// 即在执行doFilter 之前，进行权限的检查，而具体的实现已经交给accessDecisionManager
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.afterInvocation(token, null);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	public void setFisMetadataSource(
			FilterInvocationSecurityMetadataSource fisMetadataSource) {
		this.fisMetadataSource = fisMetadataSource;
	}
}