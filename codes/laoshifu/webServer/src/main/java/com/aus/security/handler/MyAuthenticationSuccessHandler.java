package com.aus.security.handler;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.common.util.JsonUtil;
import com.common.util.SysConstant;

public class MyAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	private String defaultTargetUrl;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication arg2) throws IOException,
			ServletException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put(SysConstant.JsonResult.Result.getIndex(), SysConstant.JsonResult.Success.getIndex());
		resultMap.put(SysConstant.UrlEnum.URL.getIndex(), defaultTargetUrl);
		JsonUtil.writeJson(response, resultMap);
	}
	public String getDefaultTargetUrl() {
		return defaultTargetUrl;
	}
	public void setDefaultTargetUrl(String defaultTargetUrl) {
		this.defaultTargetUrl = defaultTargetUrl;
	}

}
