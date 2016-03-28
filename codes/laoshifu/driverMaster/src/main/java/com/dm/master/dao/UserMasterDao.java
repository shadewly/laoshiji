package com.dm.master.dao;

/***********************************************************************
 * Module:  UserMasterDao.java
 * Author:  Administrator
 * Purpose: Defines the Interface UserMasterDao
 ***********************************************************************/

import com.dm.master.model.UserMaster;

public interface UserMasterDao {
	/** @param userMaster */
	public int insertUserMaster(UserMaster userMaster);

	/**
	 * 统计师傅信息条数
	 * 
	 * @param condition
	 * @return
	 */
	public int validateUserMaster(UserMaster userMaster);
}