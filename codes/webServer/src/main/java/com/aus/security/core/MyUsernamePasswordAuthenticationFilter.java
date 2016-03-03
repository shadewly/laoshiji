package com.aus.security.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.aus.dao.AccountDao;
import com.aus.model.Account;

public class MyUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {
	protected transient final Logger log = LoggerFactory.getLogger(getClass());
	public static final String VALIDATE_CODE = "validateCode";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	@Autowired
	private AccountDao accountDao;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authRequest = null;

		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("认证异常 ");
		}
		// 检测验证码
		checkValidateCode(request);

		String username = obtainUsername(request);
		String password = obtainPassword(request);
		Account account = null;
		try {
//			password = EncryptUtil.md5(password);

			// 验证用户账号与密码是否对应
			account = (Account) accountDao.selectAccount();
//			selectAccount(
//					Account.class,
//					"select * from t_base_user where user_name='" + username
//							+ "' and user_pwd='" + password + "'");
		} catch (Exception e) {
//			log.error(SysConstant.ERROR_MSG, e);
//			// e.printStackTrace();
//			throw new AuthenticationServiceException(SysConstant.ERROR_MSG);
		}
		if (account == null || !account.getPassword().equals(password)) {
			
			throw new AuthenticationServiceException("用户名或者密码错误！");
		}

		authRequest = new UsernamePasswordAuthenticationToken(username,
				password);

		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected void checkValidateCode(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String sessionValidateCode = obtainSessionValidateCode(session);
		// 让上一次的验证码失效
		session.setAttribute(VALIDATE_CODE, null);
		String validateCodeParameter = obtainValidateCodeParameter(request);
		// if (StringUtils.isEmpty(validateCodeParameter) ||
		// !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
		// throw new AuthenticationServiceException("验证码错误！");
		// }
	}

	private String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request.getParameter(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}

	protected String obtainSessionValidateCode(HttpSession session) {
		Object obj = session.getAttribute(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}
}
