package com.master.model;
/***********************************************************************
 * Module:  EROLLMENT.java
 * Author:  Administrator
 * Purpose: Defines the Class EROLLMENT
 ***********************************************************************/

import java.util.Date;

/** /  
 * /  
 * /  
 * /  
 * /  
 * /  /  
 * 报名表 */
public class Erollment {
   //id  
   private Integer id;  //发布课程号  
   private String scheduleNo;  //学习状态：报名0，开始上课1，结束2  
   private String status;  //学员这一次学习开始时间  
   private Date trainStartTime;  //学员这一次学习结束时间  
   private Date trainEndTime;  //账户  
   private Account account;

}