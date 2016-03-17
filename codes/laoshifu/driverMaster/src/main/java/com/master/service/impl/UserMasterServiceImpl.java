package com.master.service.impl;

/***********************************************************************
 * Module:  UserMasterServiceImpl.java
 * Author:  Administrator
 * Purpose: Defines the Class UserMasterServiceImpl
 ***********************************************************************/

import java.util.List;

import com.master.common.MasterConstant;
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
	public void addUserMaster(UserMaster userMaster) {
		// 校验师傅是否已经注册
		if(userMasterDao.validateUserMaster(userMaster)>0){
			throw new Exception("师傅信息已经注册");
		}
				// 校验教练车信息是否已经注册
		List<String> autoNoList=carInfoService.validateCarInfoByAutoNo(autoNoList);
		if(autoNoList.size()>0){
			StringBuffer sb=new StringBuffer();
			for(String autoNo:autoNoList){
				sb.append(autoNo);
				sb.append(",");
			}
			
			throw new Exception(sb.substring(0, sb.length()-1)+"车辆已经注册");
		}
				// 校验提交信息是否满足审核条件l
				if(){
					//满足设置为待审
					userMaster.setMasterStatus(MasterConstant.UserMasterStatus.Pending.getValue());
				}else{
					//不满足设置为待完善
					userMaster.setMasterStatus(MasterConstant.UserMasterStatus.Incomplete.getValue());
				}
				//保存师傅信息并校验结果
				if(userMasterDao.insertUserMaster(userMaster)!=1){
					throw new Exception("插入师傅信息出错");
				}
				//上传师傅图片到服务器并校验结果
				//保存教练车信息并校验结果
				carInfoService.addCarInfoList(userMaster.getCarInfoList());
				
		
		
	}
}