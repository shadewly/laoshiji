package com.bz;

import java.util.HashMap;
import java.util.Map;

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

			Map<String ,Object> aa=new HashMap<String,Object>();
			aa.put("bb", "5555");
			JsonUtil.writeJson(response, aa);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
