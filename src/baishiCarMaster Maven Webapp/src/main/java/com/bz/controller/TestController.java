package com.bz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/url")
public class TestController {
  
	@RequestMapping("/foo")
	@ResponseBody
	public void handleFoo() {
		System.out.println( "Hello world");
	}

}
