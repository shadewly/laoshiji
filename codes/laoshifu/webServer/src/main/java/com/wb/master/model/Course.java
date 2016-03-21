package com.wb.master.model;

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
	private long id; // 课程号
	private String courseNo; // 驾照类型:C1，0 C2 1
	private String licenseType; // 训练场地
	private String trainArea; // 训练开始时间
	private String trainStartTime; // 训练结束时间
	private String trainEndTime; // 训练类型：一车一人0，一车多人1
	private String trainType; // 免费服务
	private String freeService; // 其他
	private String comment; // 课程状态：启用0，禁用1
	private int status; // 徒弟数量限制
	private int apprenticeLimit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getTrainArea() {
		return trainArea;
	}

	public void setTrainArea(String trainArea) {
		this.trainArea = trainArea;
	}

	public String getTrainStartTime() {
		return trainStartTime;
	}

	public void setTrainStartTime(String trainStartTime) {
		this.trainStartTime = trainStartTime;
	}

	public String getTrainEndTime() {
		return trainEndTime;
	}

	public void setTrainEndTime(String trainEndTime) {
		this.trainEndTime = trainEndTime;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public String getFreeService() {
		return freeService;
	}

	public void setFreeService(String freeService) {
		this.freeService = freeService;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getApprenticeLimit() {
		return apprenticeLimit;
	}

	public void setApprenticeLimit(int apprenticeLimit) {
		this.apprenticeLimit = apprenticeLimit;
	}

}