package com.dm.master.service;
/***********************************************************************
 * Module:  CarInfoService.java
 * Author:  Administrator
 * Purpose: Defines the Interface CarInfoService
 ***********************************************************************/

import java.util.List;
import java.util.Map;

import com.dm.master.model.CarInfo;
import com.lsfrpc.annotation.RPCComponent;

public interface CarInfoServiceI {
   /** @param paraMap */
   CarInfo searchCarInfo(Map paraMap);
   /** @param carInfoList */
   public void addCarInfoList(List<CarInfo> carInfoList) throws Exception;
   
   /**
  	 * 通过车牌号校验教练车是否已经注册
  	 * 
  	 * @param condition
  	 * @return
  	 */
  	public List<String> validateCarInfoByAutoNo(List<String> autoNoList);

}