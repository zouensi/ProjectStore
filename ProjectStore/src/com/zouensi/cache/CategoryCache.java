package com.zouensi.cache;

import java.sql.SQLException;


public interface CategoryCache {
	/**
	 * 获取所有种类信息接口
	 * @throws SQLException 
	 */
	String getCategory() ;
	/**
	 * 设置种类接口
	 */
	void setCategory(String info);

}
