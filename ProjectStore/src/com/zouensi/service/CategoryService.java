package com.zouensi.service;

import java.sql.SQLException;


public interface CategoryService {
	/**
	 * 获取所有种类信息接口
	 */
	String getCategory() throws SQLException;
}
