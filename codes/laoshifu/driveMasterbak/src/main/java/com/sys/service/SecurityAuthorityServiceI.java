package com.sys.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.sys.model.SecurityAuthority;
import com.sys.model.vo.SecurityAuthorityVo;

public interface SecurityAuthorityServiceI{
	/**
	 * 通过权限名查询权限
	 * @param AuthorityCode
	 * @return
	 */
	public List<SecurityAuthority> findAuthoritiesByRoleCode(String roleCode)throws Exception;
	/**
	 * 获取所有的权限
	 * @return
	 * @throws Exception
	 */
	public List<SecurityAuthorityVo> findAuthorities()throws Exception;
	
	/**
	 * 修改权限信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public Map<String, Object> updateAuthority(SecurityAuthority authority) throws Exception;
	/**
	 * 添加权限信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public Map<String, Object> addAuthority(SecurityAuthority authority) throws Exception;

	/**
	 * 修改权限信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public Map<String, Object> delAuthority(String selectedIds) throws Exception;
	/**
//	 * 获取修改权限信息
//	 * 
//	 * @return
//	 * @throws IOException
//	 */
//	public Map<String, Object> getUpdateAuthorityItems(String paramData,
//			ControlItemVo controlData) throws Exception;
}
