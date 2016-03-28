package com.wb.common.util;

import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.wb.common.util.SysConstant;

/**
 * 获取上下文工具类
 * 
 * @author yuxinchen
 * @version (1.0)
 */
public class ServletContextUtil {
	private static WebApplicationContext applicationContext = ContextLoader
			.getCurrentWebApplicationContext();

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取上下文
	 * 
	 * @return
	 */
	public static ServletContext getServletContext() {

		return applicationContext.getServletContext();
	}

	/**
	 * 
	 * getServletContextObj:(简单描述方法的作用).<br/>
	 * 
	 * @param keyCode
	 * @return
	 */
	public static Object getServletContextObj(String keyCode) {

		return getServletContext().getAttribute(keyCode);
	}

	/**
	 * 
	 * getSysProperties:获取缓存中的系统字典map集合对象方法.<br>
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Properties getSysProperties() {
		return (Properties) ServletContextUtil
				.getServletContextObj(SysConstant.SYS_PROPERTIES);
	}

}