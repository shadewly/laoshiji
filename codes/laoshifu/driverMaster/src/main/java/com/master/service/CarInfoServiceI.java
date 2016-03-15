package com.master.service;
/***********************************************************************
 * Module:  CarInfoService.java
 * Author:  Administrator
 * Purpose: Defines the Interface CarInfoService
 ***********************************************************************/

import java.util.List;
import java.util.Map;

import com.master.model.CarInfo;

public interface CarInfoServiceI {
   /** @param paraMap */
   CarInfo searchCarInfo(Map paraMap);
   /** @param carInfoList */
   void addCarInfoList(List carInfoList);

}