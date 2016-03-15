package com.master.dao;

/***********************************************************************
 * Module:  CourseDao.java
 * Author:  Administrator
 * Purpose: Defines the Interface CourseDao
 ***********************************************************************/

import java.util.List;
import java.util.Map;

import com.master.model.Course;

public interface CourseDao {
   /** @param course */
   void addCourse(Course course);
   /** @param paraMap */
   Course selectCourse(Map paraMap);
   /** @param paraMap */
   List selectCourseList(Map paraMap);

}