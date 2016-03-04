package com.test.dao;

import java.util.List;

import com.common.mybatis.SqlMapper;
import com.test.model.TestModel;

public interface  TestDao extends SqlMapper {
	
	/**
	 * 查询所有menu
	 * @return
	 */
	public List<TestModel> selectTest();
}
