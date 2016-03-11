package com.aus.controller;

import java.util.HashMap;
import java.util.Map;

//对接口进行测试  
public class TestMain {
	private String url = "https://145q8w2034.iok.la:8445/webServer/";
	private String charset = "utf-8";
	private HttpClientUtil httpClientUtil = null;

	public TestMain() {
		httpClientUtil = new HttpClientUtil();
	}

	public void test() {
		String httpOrgCreateTest = url + "accountController?register";
		Map<String, String> createMap = new HashMap<String, String>();
		createMap.put("authuser", "*****");
		createMap.put("authpass", "*****");
		createMap.put("orgkey", "****");
		createMap.put("orgname", "****");
		String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,
				createMap, charset);
		System.out.println("result:" + httpOrgCreateTestRtn);
	}

	public static void main(String[] args) {
		TestMain main = new TestMain();
		main.test();
	}
}