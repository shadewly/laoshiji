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

}