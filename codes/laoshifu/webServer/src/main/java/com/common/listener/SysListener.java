package com.common.listener;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.common.util.SysConstant;



/**
 * 
 * 系统启动监听类
 * @author yuxinchen
 * @version (1.0)
 */
public class SysListener implements ServletContextListener, InitializingBean {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {

			propertiesInit(arg0);
	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * propertiesInit:(配置信息初始化).<br>
	 * @param arg0
	 * @throws Exception
	 */
	private void propertiesInit(ServletContextEvent arg0) throws Exception {
		WebApplicationContext rwp = WebApplicationContextUtils
				.getRequiredWebApplicationContext(arg0.getServletContext());
		Resource resource = new ClassPathResource("/properties/sso.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);

		arg0.getServletContext().setAttribute(SysConstant.SYS_PROPERTIES,
				props);
	}
	
	
    
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

}
