package com.zouensi.dao;

import java.sql.SQLException;
import java.util.List;

import com.zouensi.domain.Category;

public interface CategoryDao {
	/**
	 * 获取所有种类信息接口
	 * @throws SQLException 
	 */
	List<Category> getCategory() throws SQLException;
}
