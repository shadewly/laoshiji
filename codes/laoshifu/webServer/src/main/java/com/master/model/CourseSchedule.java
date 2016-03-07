package com.master.model;

/***********************************************************************
 * Module:  CourseSchedule.java
 * Author:  Administrator
 * Purpose: Defines the Class CourseSchedule
 ***********************************************************************/

import java.util.*;

/**
 * / / / / / / / 课程安排
 */
public class CourseSchedule {
	// id
	private int id; // 发布课程号
	private String scheduleNo; // 课程号
	private String courseNo; // 驾照类型:C1，0 C2 1
	private String licenseType; // 训练场地
	private String trainArea; // 训练开始时间
	private java.util.Date trainStartTime; // 训练结束时间
	private java.util.Date trainEndTime; // 训练类型：一车一人0，一车多人1
	private String trainType; // 免费服务
	private String freeService; // 其他
	private String comment; // 徒弟数量限制
	private int apprenticeLimit; // 课程状态：发布0，取消1
	private String status; // 已报名数量
	private int enrollment; // 报名人员信息
	private List erollmentList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
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

	public java.util.Date getTrainStartTime() {
		return trainStartTime;
	}

	public void setTrainStartTime(java.util.Date trainStartTime) {
		this.trainStartTime = trainStartTime;
	}

	public java.util.Date getTrainEndTime() {
		return trainEndTime;
	}

	public void setTrainEndTime(java.util.Date trainEndTime) {
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

	public int getApprenticeLimit() {
		return apprenticeLimit;
	}

	public void setApprenticeLimit(int apprenticeLimit) {
		this.apprenticeLimit = apprenticeLimit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(int enrollment) {
		this.enrollment = enrollment;
	}

	public List getErollmentList() {
		return erollmentList;
	}

	public void setErollmentList(List erollmentList) {
		this.erollmentList = erollmentList;
	}

}