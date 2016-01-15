package com.bz;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.core.util.JsonUtil;

@Controller
@RequestMapping("/menu")
public class TestController {

	@RequestMapping(params = "aa")
	public void aa() {
		String aa = "你好";
	}

	@RequestMapping(params = "bb")
	public void createmenu(HttpServletResponse response, Integer fatherId) {
		try {

			JsonUtil.writeJson(response, "5555");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
