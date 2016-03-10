package com.aus.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aus.model.Account;
import com.aus.service.AccountServiceI;
import com.common.util.JsonUtil;

@Controller
@RequestMapping("/accountController")
public class AccountController {
	@Autowired
	private AccountServiceI accountService;

	@RequestMapping(params = "login")
	public void login(HttpServletResponse response, Account account) {
		try {
			account.setAccountNo("yxc");
			account.setPassword("123");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("result", accountService.login(account));

			JsonUtil.writeJson(response, resultMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
