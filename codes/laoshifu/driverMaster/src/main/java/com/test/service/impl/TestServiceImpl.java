package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.TestDao;
import com.test.model.TestModel;
import com.test.service.TestServiceI;

/**
 * 目录服务类
 * 
 * @author yuxinchen
 * 
 */
@Service(value = "testService")
@Transactional
public class TestServiceImpl implements TestServiceI {

	@Autowired
	@Qualifier("testDao")
	protected TestDao testDao;

	public List<TestModel> aa() throws Exception {
		List<TestModel> list=testDao.selectTest();
		System.out.println(list.size());
		return list;
	}


	
}
