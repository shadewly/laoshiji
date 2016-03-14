package com.aus.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aus.model.Account;

@Controller
@RequestMapping("/test2")
public class Test {

	/**
	 * 注册
	 * @param response
	 * @param account
	 */
	@RequestMapping(params = "rr")
	public void register(HttpServletResponse response, Account account) {
		try {
			System.out.println("55555555555");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
