package com.master.service;

/***********************************************************************
 * Module:  UserMasterServiceI.java
 * Author:  Administrator
 * Purpose: Defines the Interface UserMasterServiceI
 ***********************************************************************/

import java.util.HashMap;

import com.master.model.UserMaster;

public interface UserMasterServiceI {

	/**
	 * 统计师傅数量
	 * 
	 * @param condition
	 * @return
	 */
	public int countUserMaster(HashMap<String, Object> condition);

	/**
	 * 添加师傅信息
	 * @param userMaster
	 */
	public void addUserMaster(UserMaster userMaster);

}