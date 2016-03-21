package com.aus.security.handler;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.dm.common.util.JsonUtil;
import com.dm.common.util.SysConstant;

public class MyAuthenticationFailureHandler implements
		AuthenticationFailureHandler {
	private String errorPage;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authenticationException)
			throws IOException, ServletException {
		boolean isAjax = false;
		String requestType = request.getHeader("X-Requested-With");

		if (null != requestType && "XMLHttpRequest".equals(requestType)) {
			isAjax = true;
		}

		if (isAjax) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			resultMap.put(SysConstant.JsonResult.Result.getIndex(), SysConstant.JsonResult.Fail.getIndex());
			resultMap.put(SysConstant.JsonResult.Msg.getIndex(), authenticationException.getMessage().toString());
			JsonUtil.writeJson(response, resultMap);
		} else if (!response.isCommitted()) {
			if (errorPage != null) {
				// Put exception into request scope (perhaps of use to a view)
				request.setAttribute(WebAttributes.ACCESS_DENIED_403,
						authenticationException);

				// Set the 403 status code.
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);

				// forward to error page.
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(errorPage);
				dispatcher.forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN,
						authenticationException.getMessage());
			}
		}

	}
	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}

		this.errorPage = errorPage;
	}
}
