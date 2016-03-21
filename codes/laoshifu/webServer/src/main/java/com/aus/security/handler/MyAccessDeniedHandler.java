package com.aus.security.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.dm.common.util.JsonUtil;
import com.dm.common.util.SysConstant;

public class MyAccessDeniedHandler implements AccessDeniedHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.access.AccessDeniedHandler#handle(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.access.AccessDeniedException)
	 */
	private String errorPage;

	// ~ Methods
	// ========================================================================================================

	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		boolean isAjax = false;
		String requestType = request.getHeader("X-Requested-With");

		if (null != requestType && "XMLHttpRequest".equals(requestType)) {
			isAjax = true;
		}

		if (isAjax) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			resultMap.put(SysConstant.JsonResult.Result.getIndex(), SysConstant.JsonResult.Fail.getIndex());
			resultMap.put(SysConstant.JsonResult.Msg.getIndex(), accessDeniedException.getMessage().toString());
			JsonUtil.writeJson(response, resultMap);
		} else if (!response.isCommitted()) {
			if (errorPage != null) {
				// Put exception into request scope (perhaps of use to a view)
				request.setAttribute(WebAttributes.ACCESS_DENIED_403,
						accessDeniedException);

				// Set the 403 status code.
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);

				// forward to error page.
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(errorPage);
				dispatcher.forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN,
						accessDeniedException.getMessage());
			}
		}
	}

	/**
	 * The error page to use. Must begin with a "/" and is interpreted relative
	 * to the current context root.
	 * 
	 * @param errorPage
	 *            the dispatcher path to display
	 * 
	 * @throws IllegalArgumentException
	 *             if the argument doesn't comply with the above limitations
	 */
	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}

		this.errorPage = errorPage;
	}

}
