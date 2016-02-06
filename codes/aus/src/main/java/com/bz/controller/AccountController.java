package com.bz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bz.service.AccountServiceI;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountServiceI accountService;



}
