package com.dm.master.service.impl;

/***********************************************************************
 * Module:  CarInfoServiceImpl.java
 * Author:  Administrator
 * Purpose: Defines the Class CarInfoServiceImpl
 ***********************************************************************/

import java.util.List;
import java.util.Map;

import com.dm.master.dao.CarInfoDao;
import com.dm.master.model.CarInfo;
import com.dm.master.service.CarInfoServiceI;
import com.lsfrpc.annotation.RPCComponent;

/**
 * / / / / / / / 车辆信息服务实现类
 */
@RPCComponent(CarInfoServiceI.class)
public class CarInfoServiceImpl implements CarInfoServiceI {
	// 车辆信息dao接口对象
	private CarInfoDao carInfoDao;

	/** @param paraMap */
	public List searchCarInfoList(Map paraMap) {
		// TODO: implement
		return null;
	}

	/** @param carInfoList 
	 * @throws Exception */
	public void addCarInfoList(List carInfoList) throws Exception {
		// 插入教练车信息
		if(carInfoDao.insertCarInfoList(carInfoList)!=carInfoList.size()){
			throw new Exception("插入车辆信息出错");
		}
		// 上传教练车图片信息到图片服务器
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