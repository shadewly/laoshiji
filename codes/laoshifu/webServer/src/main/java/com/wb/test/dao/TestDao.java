package com.wb.test.dao;

import java.util.List;

import com.wb.common.mybatis.SqlMapper;
import com.wb.test.model.TestModel;

public interface  TestDao extends SqlMapper {
	
	/**
	 * 查询所有menu
	 * @return
	 */
	public List<TestModel> selectTest();
}
