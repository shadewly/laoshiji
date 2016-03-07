package com.master.model;

/***********************************************************************
 * Module:  CourseSchedule.java
 * Author:  Administrator
 * Purpose: Defines the Class CourseSchedule
 ***********************************************************************/

import java.util.*;

/** @pdOid 2789b448-52c5-4571-a1d1-0f54421f2a88 */
public class CourseSchedule {
	/** @pdOid ff768de7-df4d-4ef9-8511-d79f9e414013 */
	private Integer id;
	/** @pdOid 9bcd6275-868f-4676-8bd6-d68ca8706a39 */
	private String scheduleNo;
	/** @pdOid 3b8d2696-fa38-4bd5-b2c0-5196eacb2fa7 */
	private String courseNo;
	/** @pdOid b97ac2ce-cf5c-4edd-8683-ab30b8e4b375 */
	private String licenseType;
	/** @pdOid a55b427c-9871-49b8-91b4-07979960a417 */
	private String trainArea;
	/** @pdOid 12dd6c3d-d962-493d-aada-848cab79332d */
	private Date trainStartTime;
	/** @pdOid 701e951d-96f7-4d02-9f04-f42b282d92e5 */
	private Date trainEndTime;
	/** @pdOid 01399cb9-fe59-48ba-b0c5-4df1dd0b8c9d */
	private String trainType;
	/** @pdOid b5f2a98d-38be-42c1-a41f-6b7c3ccb5c0a */
	private String freeService;
	/** @pdOid b3068e0c-bbd7-4419-896f-58811ae800b6 */
	private String comment;
	/** @pdOid a83815c9-01b1-4cf0-9453-bf4e31f3dd25 */
	private Integer apprenticeLimit;
	/** @pdOid cd4e7774-d9ff-480b-a132-ca9b2404b751 */
	private String status;
	/** @pdOid 47dff2ce-1d3d-4ad5-ba3a-e99e73df3a22 */
	private Integer enrollment;
	/** @pdOid 55ee8427-d6a0-46f7-981e-e665658e9530 */
	private List erollmentList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Date getTrainStartTime() {
		return trainStartTime;
	}

	public void setTrainStartTime(Date trainStartTime) {
		this.trainStartTime = trainStartTime;
	}

	public Date getTrainEndTime() {
		return trainEndTime;
	}

	public void setTrainEndTime(Date trainEndTime) {
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

	public Integer getApprenticeLimit() {
		return apprenticeLimit;
	}

	public void setApprenticeLimit(Integer apprenticeLimit) {
		this.apprenticeLimit = apprenticeLimit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Integer enrollment) {
		this.enrollment = enrollment;
	}

	public List getErollmentList() {
		return erollmentList;
	}

	public void setErollmentList(List erollmentList) {
		this.erollmentList = erollmentList;
	}

}