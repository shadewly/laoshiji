package com.core.server;

import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;

public class Test {
public static void main(String args[]){
	MockServletContext servletContext = new MockServletContext();
	MockServletConfig servletConfig = new MockServletConfig(servletContext);
	System.out.println("dddd");
}
}
