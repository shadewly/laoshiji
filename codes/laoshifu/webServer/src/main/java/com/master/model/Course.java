package com.master.model;

/***********************************************************************
 * Module:  Course.java
 * Author:  Administrator
 * Purpose: Defines the Class Course
 ***********************************************************************/

import java.util.*;

/** @pdOid 7035aec3-e9fc-4da3-8f69-ab5b3f913e96 */
public class Course {
	/** @pdOid 3c36aff2-2ccb-4323-a6ed-09efbaa75162 */
	private Long id;
	/** @pdOid 6d01bf0b-2c4e-47cc-8348-f3d1894eafbf */
	private String courseNo;
	/** @pdOid 0c7ac804-381a-44f5-8830-9cc463d9428c */
	private String licenseType;
	/** @pdOid bb0fc8db-2b60-4ef1-a970-c260492e1444 */
	private String trainArea;
	/** @pdOid 2535aeb6-079d-4303-b51d-c75c9009d045 */
	private String trainStartTime;
	/** @pdOid 2b2cbb82-5199-498d-9f7f-b1dd7b288fac */
	private String trainEndTime;
	/** @pdOid b2bcdca2-e99e-4b53-8de6-614c15f71eb1 */
	private String trainType;
	/** @pdOid e4599b7e-9231-4381-9605-68f41a4eda3c */
	private String freeService;
	/** @pdOid a65367fb-36d9-4019-bacb-3d4044133e52 */
	private String comment;
	/** @pdOid 542f6f0b-1332-4456-a46b-dd2094fbbc83 */
	private Integer status;
	/** @pdOid ed222f90-72e6-4090-aacc-71797905a311 */
	private Integer apprenticeLimit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getApprenticeLimit() {
		return apprenticeLimit;
	}

	public void setApprenticeLimit(Integer apprenticeLimit) {
		this.apprenticeLimit = apprenticeLimit;
	}

}