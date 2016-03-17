package com.master.dao;

/***********************************************************************
 * Module:  UserMasterDao.java
 * Author:  Administrator
 * Purpose: Defines the Interface UserMasterDao
 ***********************************************************************/

import java.util.Map;

import com.master.model.UserMaster;

public interface UserMasterDao {
	/** @param userMaster */
	public int insertUserMaster(UserMaster userMaster);

	/**
	 * 统计师傅信息条数
	 * 
	 * @param condition
	 * @return
	 */
	public int countUserMaster(Map<String, Object> condition);
}