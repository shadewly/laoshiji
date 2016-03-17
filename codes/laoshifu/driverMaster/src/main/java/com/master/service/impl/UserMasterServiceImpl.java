package com.master.service.impl;

/***********************************************************************
 * Module:  UserMasterServiceImpl.java
 * Author:  Administrator
 * Purpose: Defines the Class UserMasterServiceImpl
 ***********************************************************************/

import java.util.HashMap;

import com.master.dao.UserMasterDao;
import com.master.model.UserMaster;
import com.master.service.CarInfoServiceI;
import com.master.service.UserMasterServiceI;

/**
 * 师傅用户信息服务实现类
 */
public class UserMasterServiceImpl implements UserMasterServiceI {
	// 师傅用户dao接口对象
	private UserMasterDao userMasterDao; // 车辆信息dao接口对象
	private CarInfoServiceI carInfoService;

	@Override
	public int countUserMaster(HashMap<String, Object> condition) {

		return userMasterDao.countUserMaster(condition);
	}

	@Override
	public void addUserMaster(UserMaster userMaster) {
		// 校验师傅是否已经注册
		userMasterDao.countUserMaster(condition)
				// 校验教练车信息是否已经注册
		carInfoService.validateCarInfoByAutoNo(autoNoList)
				// 校验提交信息是否满足审核条件l
				if(){
					//满足
					
				}else{
					//不满足
					userMaster
				}
		
	}
}