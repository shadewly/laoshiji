package com.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.service.TestServiceI;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private TestServiceI testService;

	@RequestMapping(params = "aa")
	public void aa() {
		String aa = "你好";
		System.out.println(aa);
	}

	@RequestMapping(params = "bb")
	public void createmenu(HttpServletRequest request,HttpServletResponse response, Integer fatherId) {
		try {

			Map<String, Object> aa = new HashMap<String, Object>();
			aa.put("bb", testService.aa());
			System.out.println("----->"+Thread.currentThread().getName());
			response.sendRedirect("https://localhost:8449");
//			request.getRequestDispatcher("https://localhost:8449").forward(request, response);
//			JsonUtil.writeJson(response, aa);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
//	@RequestMapping(params = "cc")
//	public void cc(HttpServletResponse response, Integer fatherId) {
//		try {
//
//			
//			System.out.println("----->"+Thread.currentThread().getName());
//			
//			TestModel t=testService.aa().get(0);
//			
//			Test.TestModel.Builder builder= Test.TestModel.newBuilder();
//			
//			BeanUtils.copyProperties(t, builder);
//			Test.TestModel  testModel= builder.build();
//			
//
//			byte[] buffer=testModel.toByteArray();
//			
//			response.setContentType("application/octet-stream");
//			response.setCharacterEncoding("utf-8");		
//		
//			response.getWriter().print(buffer);
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//	}

}
