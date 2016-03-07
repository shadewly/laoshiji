package com.master.model;

/***********************************************************************
 * Module:  Account.java
 * Author:  Administrator
 * Purpose: Defines the Class Account
 ***********************************************************************/

import java.util.*;

/**
 * / / / / / / / 账户
 */
public class Account {
	// id
	private long id; // 账号
	private String accountNo; // 密码
	private String password; // 状态
	private String status; // 师傅用户
	private UserMaster userMaster;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserMaster getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

}