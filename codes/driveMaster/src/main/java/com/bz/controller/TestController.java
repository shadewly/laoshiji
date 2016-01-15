package com.bz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	}

	@RequestMapping(params = "bb")
	public void createmenu(HttpServletResponse response, Integer fatherId) {
		try {

			Map<String, Object> aa = new HashMap<String, Object>();
			aa.put("bb", "5555");
			JsonUtil.writeJson(response, aa);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
