package com;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
public class ServerBootstrap {
	public static void main(String[] args) {
		System.out
				.println("-------------------------start----------------------");
		new ClassPathXmlApplicationContext("classpath*:spring/spring-core.xml");
	}
}
