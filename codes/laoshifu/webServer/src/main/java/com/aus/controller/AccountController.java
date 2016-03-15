package com.aus.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aus.model.Account;
import com.aus.service.AccountServiceI;
import com.common.util.JsonUtil;
import com.common.util.MessageUtil;

@Controller
@RequestMapping("/accountController")
public class AccountController {
	@Autowired
	private AccountServiceI accountService;

	/**
	 * 登录
	 * @param response
	 * @param account
	 */
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
	
	/**
	 * 注册
	 * @param response
	 * @param account
	 */
	@RequestMapping(params = "register")
	public void register(HttpServletResponse response, Account account) {
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			accountService.register(account);
			resultMap.put("msg", MessageUtil.getMsg("MSG_ACCOUNT_0001"));
			JsonUtil.writeJson(response, resultMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
