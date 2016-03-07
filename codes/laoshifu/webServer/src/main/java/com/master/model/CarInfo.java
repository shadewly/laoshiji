package com.master.model;

/***********************************************************************
 * Module:  CarInfo.java
 * Author:  Administrator
 * Purpose: Defines the Class CarInfo
 ***********************************************************************/

import java.util.*;

/**
 * / / / / / / / 车辆信息
 */
public class CarInfo {
	// id
	private long id; // 车牌号
	private long autoNo; // 教练车品牌及型号
	private String autoBrandType; // 行驶证正面图片地址
	private String drivingLicenseFront; // 行驶证反面图片地址
	private String drivingLicenseReverse; // 教练车检验合格证明图片地址
	private String certificate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAutoNo() {
		return autoNo;
	}

	public void setAutoNo(long autoNo) {
		this.autoNo = autoNo;
	}

	public String getAutoBrandType() {
		return autoBrandType;
	}

	public void setAutoBrandType(String autoBrandType) {
		this.autoBrandType = autoBrandType;
	}

	public String getDrivingLicenseFront() {
		return drivingLicenseFront;
	}

	public void setDrivingLicenseFront(String drivingLicenseFront) {
		this.drivingLicenseFront = drivingLicenseFront;
	}

	public String getDrivingLicenseReverse() {
		return drivingLicenseReverse;
	}

	public void setDrivingLicenseReverse(String drivingLicenseReverse) {
		this.drivingLicenseReverse = drivingLicenseReverse;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

}