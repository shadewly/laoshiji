package com.master.dao;

/***********************************************************************
 * Module:  CarInfoDao.java
 * Author:  Administrator
 * Purpose: Defines the Interface CarInfoDao
 ***********************************************************************/

import java.util.*;

public interface CarInfoDao {
   /** @param paraMap */
   List selectCarInfoList(Map paraMap);
   /** @param carInfoList */
   int insertCarInfoList(List carInfoList);

}