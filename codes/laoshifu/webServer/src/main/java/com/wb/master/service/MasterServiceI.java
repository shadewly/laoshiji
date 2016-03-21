package com.wb.master.service;

/***********************************************************************
 * Module:  UserMasterServiceI.java
 * Author:  Administrator
 * Purpose: Defines the Interface UserMasterServiceI
 ***********************************************************************/

import com.wb.master.model.UserMaster;

public interface MasterServiceI {
   /** @param userMaster */
   public void addUserMaster(UserMaster userMaster) throws Exception ;

}