package com.master.service.impl;

/***********************************************************************
 * Module:  CarInfoServiceImpl.java
 * Author:  Administrator
 * Purpose: Defines the Class CarInfoServiceImpl
 ***********************************************************************/

import java.util.List;
import java.util.Map;

import com.master.dao.CarInfoDao;
import com.master.model.CarInfo;
import com.master.service.CarInfoServiceI;

/**
 * / / / / / / / 车辆信息服务实现类
 */
public class CarInfoServiceImpl implements CarInfoServiceI {
	// 车辆信息dao接口对象
	private CarInfoDao carInfoDao;

	/** @param paraMap */
	public List searchCarInfoList(Map paraMap) {
		// TODO: implement
		return null;
	}

	/** @param carInfoList */
	public void addCarInfoList(List carInfoList) {
		// TODO: implement
	}

	@Override
	public CarInfo searchCarInfo(Map paraMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> validateCarInfoByAutoNo(List<String> autoNoList) {
		// TODO Auto-generated method stub
		return carInfoDao.validateCarInfoByAutoNo(autoNoList);
	}

	

}