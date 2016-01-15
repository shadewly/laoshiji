package com.core.server;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringServer implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	public static void init() {
		applicationContext = new ClassPathXmlApplicationContext(
				"classpath*:application-*.xml");
	}

	public static <T> T getBean(Class<T> paramClass) {
		return applicationContext.getBean(paramClass);
	}

	public static Object getBean(String paramString) {
		return applicationContext.getBean(paramString);
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringServer.applicationContext = applicationContext;
		
	}



}
