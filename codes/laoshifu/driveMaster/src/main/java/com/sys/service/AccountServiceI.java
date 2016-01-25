package com.sys.service;

import java.io.IOException;
import java.util.Map;

import com.sys.model.Account;

public interface AccountServiceI {

//	/**
//	 * 获取修改用户信息
//	 * 
//	 * @return
//	 * @throws IOException
//	 */
//	public Map<String, Object> getUpdateBaseUserItems(String paramData,
//			ControlItemVo controlData) throws Exception;

	/**
	 * 修改用户信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public Map<String, Object> updateBaseUser(Account baseUser)
			throws Exception;

	/**
	 * 添加用户信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public Map<String, Object> addBaseUser(Account baseUser) throws Exception;

	/**
	 * 删除用户信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public Map<String, Object> delBaseUser(String selectedIds) throws Exception;
}
