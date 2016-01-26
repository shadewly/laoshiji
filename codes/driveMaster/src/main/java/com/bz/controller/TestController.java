package com.bz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bz.model.Test;
import com.bz.model.TestModel;
import com.bz.service.TestServiceI;
import com.core.util.JsonUtil;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private TestServiceI testService;

	@RequestMapping(params = "aa")
	public void aa() {
		String aa = "你好";
		System.out.println("Hi "+aa);
	}

	@RequestMapping(params = "bb")
	public void createmenu(HttpServletResponse response, Integer fatherId) {
		try {

			Map<String, Object> aa = new HashMap<String, Object>();
			aa.put("bb", testService.aa());
			System.out.println("----->"+Thread.currentThread().getName());
			
			JsonUtil.writeJson(response, aa);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	@RequestMapping(params = "cc")
	public void cc(HttpServletResponse response, Integer fatherId) {
		try {

			
			System.out.println("----->"+Thread.currentThread().getName());
			
			TestModel t=testService.aa().get(0);
			
			Test.TestModel.Builder builder= Test.TestModel.newBuilder();
			
			BeanUtils.copyProperties(t, builder);
			Test.TestModel  testModel= builder.build();
			

			byte[] buffer=testModel.toByteArray();
			
			response.setContentType("application/octet-stream");
			response.setCharacterEncoding("utf-8");		
		
			response.getWriter().print(buffer);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
