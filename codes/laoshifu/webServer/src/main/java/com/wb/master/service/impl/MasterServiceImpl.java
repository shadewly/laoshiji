package com.wb.master.service.impl;

/***********************************************************************
 * Module:  UserMasterServiceImpl.java
 * Author:  Administrator
 * Purpose: Defines the Class UserMasterServiceImpl
 ***********************************************************************/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dm.master.model.UserMaster;
import com.dm.master.service.UserMasterServiceI;
import com.lsfrpc.bean.RPCBeanManager;
import com.wb.master.service.MasterServiceI;

/**
 * 师傅用户信息服务实现类
 */
@Service(value = "masterService")
@Transactional
public class MasterServiceImpl implements MasterServiceI {
	@Autowired
	private RPCBeanManager manager;

	/** @param userMaster 
	 * @throws Exception */
	public void addUserMaster(UserMaster userMaster) throws Exception {
		UserMasterServiceI userMasterService = manager.getBean(UserMasterServiceI.class);
		userMasterService.addUserMaster(userMaster);
	}

}