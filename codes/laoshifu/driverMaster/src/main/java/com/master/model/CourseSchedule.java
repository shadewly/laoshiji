package com.master.model;

/***********************************************************************
 * Module:  CourseSchedule.java
 * Author:  Administrator
 * Purpose: Defines the Class CourseSchedule
 ***********************************************************************/

import java.util.*;

/** /  
 * /  
 * /  
 * /  
 * /  
 * /  /  
 * 课程安排 */
public class CourseSchedule {
   //id  
   private Integer id;  //发布课程号  
   private String scheduleNo;  //课程号  
   private String courseNo;  //驾照类型:C1，0 C2 1  
   private String licenseType;  //训练场地  
   private String trainArea;  //训练开始时间  
   private Date trainStartTime;  //训练结束时间  
   private Date trainEndTime;  //训练类型：一车一人0，一车多人1  
   private String trainType;  //免费服务  
   private String freeService;  //其他  
   private String comment;  //徒弟数量限制  
   private Integer apprenticeLimit;  //课程状态：发布0，取消1  
   private String status;  //已报名数量  
   private Integer enrollment;  //报名人员信息  
   private List erollmentList;

}