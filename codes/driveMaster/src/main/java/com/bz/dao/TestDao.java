package com.bz.dao;

import java.util.List;

import com.bz.model.Test;
import com.core.mybatis.SqlMapper;

public interface  TestDao extends SqlMapper {
	
	/**
	 * 查询所有menu
	 * @return
	 */
	public List<Test> selectTest();
}
