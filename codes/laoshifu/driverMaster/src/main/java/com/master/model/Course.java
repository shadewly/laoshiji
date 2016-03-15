package com.master.model;

/***********************************************************************
 * Module:  Course.java
 * Author:  Administrator
 * Purpose: Defines the Class Course
 ***********************************************************************/

import java.util.*;

/**
 * / / / / / / / 课程信息
 */
public class Course {
	// id
	private Long id; // 课程号
	private String courseNo; // 驾照类型:C1，0 C2 1
	private String licenseType; // 训练场地
	private String trainArea; // 训练开始时间
	private String trainStartTime; // 训练结束时间
	private String trainEndTime; // 训练类型：一车一人0，一车多人1
	private String trainType; // 免费服务
	private String freeService; // 其他
	private String comment; // 课程状态：启用0，禁用1
	private Integer status; // 徒弟数量限制
	private Integer apprenticeLimit;

}