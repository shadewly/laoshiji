package com.aus.service.impl;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aus.common.AusConstant;
import com.aus.dao.AccountDao;
import com.aus.model.Account;
import com.aus.security.core.CasRestClient;
import com.aus.service.AccountServiceI;
import com.common.util.DefaultPasswordEncoder;
import com.common.util.MessageUtil;
import com.common.util.ServletContextUtil;
import com.common.util.SysConstant;

/**
 *
 * 
 * @author yuxinchen
 * 
 */
@Service(value = "accountService")
@Transactional
public class AccountServiceImpl implements AccountServiceI {
	@Autowired
	private AccountDao accountDao;

	@Override
	public boolean login(Account account) {

		Properties pop = ServletContextUtil.getSysProperties();

		String ticket = CasRestClient.getTicket(
				pop.getProperty(SysConstant.SSO_TICKETS),
				account.getAccountNo(), account.getPassword(),
				pop.getProperty(SysConstant.SSO_AUS_SERVICE));
		return CasRestClient.ticketValidate(
				pop.getProperty(SysConstant.SSO_PROXY_VALIDATE), ticket,
				pop.getProperty(SysConstant.SSO_AUS_SERVICE));

	}

	@Override
	public void register(Account account) throws Exception {

		// 短信码校验??

		// 校验账号是否存在
		if (validateAccount(account) > 0) {
			throw new Exception(MessageUtil.getMsg("ERROR_ACCOUNT_0002"));
		}
		// MD5加密处理??

		account.setPassword(DefaultPasswordEncoder.encode(account.getPassword()));
		account.setStatus(AusConstant.AccountStatus.Enable.getValue());
		// 插入数据库
		if (accountDao.insertAccount(account) != 1) {
			throw new Exception(MessageUtil.getMsg("ERROR_ACCOUNT_0001"));
		}

	}

	/**
	 * 校验账号是否存在
	 */
	private int validateAccount(Account account) {

		return accountDao.countAccount(account);

	}

}
