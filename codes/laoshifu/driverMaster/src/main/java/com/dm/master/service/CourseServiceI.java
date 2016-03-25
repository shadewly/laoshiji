package com.dm.master.service;

/***********************************************************************
 * Module:  CourseServiceI.java
 * Author:  Administrator
 * Purpose: Defines the Interface CourseServiceI
 ***********************************************************************/

import java.util.List;
import java.util.Map;

import com.dm.master.model.Course;

public interface CourseServiceI {
	/** @param course */
	void addCourse(Course course);

	/** @param paraMap */
	Course searchCourse(Map paraMap);

	List searchCourseList();

}