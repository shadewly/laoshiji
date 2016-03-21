package com.wb.common.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * 
 * 消息工具
 * 
 * @author yuxinchen
 * @version (1.0)
 */
public class MessageUtil {

	/**
	 * 
	 * getMsg:获取消息.<br>
	 * 
	 * @param key
	 *            消息键
	 * @return
	 */
	public static String getMsg(String key) {
		MessageSource messageSource = ServletContextUtil
				.getApplicationContext().getBean(MessageSource.class);
		return messageSource.getMessage(key, null, Locale.CHINA);
	}

}
