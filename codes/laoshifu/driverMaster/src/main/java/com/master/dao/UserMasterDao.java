package com.master.dao;

/***********************************************************************
 * Module:  UserMasterDao.java
 * Author:  Administrator
 * Purpose: Defines the Interface UserMasterDao
 ***********************************************************************/

import com.master.model.UserMaster;

public interface UserMasterDao {
   /** @param userMaster */
   int insertUserMaster(UserMaster userMaster);

}