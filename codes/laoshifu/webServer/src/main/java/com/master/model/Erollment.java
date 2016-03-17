package com.master.model;

/***********************************************************************
 * Module:  EROLLMENT.java
 * Author:  Administrator
 * Purpose: Defines the Class EROLLMENT
 ***********************************************************************/

import com.aus.model.Account;

/**
 * / / / / / / / 报名表
 */
public class Erollment {
	// id
	private int id; // 发布课程号
	private String scheduleNo; // 学习状态：报名0，开始上课1，结束2
	private String status; // 学员这一次学习开始时间
	private java.util.Date trainStartTime; // 学员这一次学习结束时间
	private java.util.Date trainEndTime; // 账户
	private Account account;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}