package com.master.service.impl;

/***********************************************************************
 * Module:  UserMasterServiceImpl.java
 * Author:  Administrator
 * Purpose: Defines the Class UserMasterServiceImpl
 ***********************************************************************/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsfrpc.bean.RPCBeanManager;
import com.lsfrpc.service.HelloService;
import com.master.model.UserMaster;
import com.master.service.UserMasterServiceI;

/**
 * 师傅用户信息服务实现类
 */
@Service(value = "userMasterService")
@Transactional
public class UserMasterServiceImpl implements UserMasterServiceI {
	@Autowired
	private RPCBeanManager manager;

	/** @param userMaster */
	public void addUserMaster(UserMaster userMaster) {
		 HelloService helloService = manager.getBean(HelloService.class);
		 helloService.hello("222");
	}

}