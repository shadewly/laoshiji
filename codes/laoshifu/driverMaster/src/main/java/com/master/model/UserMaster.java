package com.master.model;

/***********************************************************************
 * Module:  UserMaster.java
 * Author:  Administrator
 * Purpose: Defines the Class UserMaster
 ***********************************************************************/

import java.util.*;

/**
 * / / / / / / / 师傅用户信息
 */
public class UserMaster {
	// id
	private long id; // 昵称
	private String userName; // 手机
	private String mobile; // 邮箱
	private String email; // 性别
	private Integer gender; // 头像图片地址
	private String avatar; // 真实姓名
	private String realName; // 身份证号
	private String identity; // 身份证正面图片地址
	private String identityFront; // 身份证反面图片地址
	private String identityReverse; // 驾龄
	private Integer experience; // 驾照相片正面图片地址
	private String licenseFront; // 驾照相片反面图片地址
	private String licenseReverse; // 驾照副证相片正面图片地址
	private String licenseAssociateFront; // 驾照副证相片反面图片地址
	private String licenseAssociateReverse; // 教练证相片地址
	private String masterCard; // 教练车
	private List carInfoList;
	private String masterStatus; // 教练信息状态

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getIdentityFront() {
		return identityFront;
	}

	public void setIdentityFront(String identityFront) {
		this.identityFront = identityFront;
	}

	public String getIdentityReverse() {
		return identityReverse;
	}

	public void setIdentityReverse(String identityReverse) {
		this.identityReverse = identityReverse;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public String getLicenseFront() {
		return licenseFront;
	}

	public void setLicenseFront(String licenseFront) {
		this.licenseFront = licenseFront;
	}

	public String getLicenseReverse() {
		return licenseReverse;
	}

	public void setLicenseReverse(String licenseReverse) {
		this.licenseReverse = licenseReverse;
	}

	public String getLicenseAssociateFront() {
		return licenseAssociateFront;
	}

	public void setLicenseAssociateFront(String licenseAssociateFront) {
		this.licenseAssociateFront = licenseAssociateFront;
	}

	public String getLicenseAssociateReverse() {
		return licenseAssociateReverse;
	}

	public void setLicenseAssociateReverse(String licenseAssociateReverse) {
		this.licenseAssociateReverse = licenseAssociateReverse;
	}

	public String getMasterCard() {
		return masterCard;
	}

	public void setMasterCard(String masterCard) {
		this.masterCard = masterCard;
	}

	public List getCarInfoList() {
		return carInfoList;
	}

	public void setCarInfoList(List carInfoList) {
		this.carInfoList = carInfoList;
	}

	public String getMasterStatus() {
		return masterStatus;
	}

	public void setMasterStatus(String masterStatus) {
		this.masterStatus = masterStatus;
	}

}