package com.zouensi.dao.impl;


import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zouensi.dao.CategoryDao;
import com.zouensi.domain.Category;
import com.zouensi.utils.QueryRunnerUtils;

public class CategoryDaoImpl implements CategoryDao {
	private QueryRunner qr = QueryRunnerUtils.getQueryRunnerWithDs();
	/**
	 * 获取分类信息
	 */
	@Override
	public List<Category> getCategory() throws SQLException {
		String sql = "select * from category";
		List<Category> categorys = qr.query(sql, new BeanListHandler<>(Category.class));
		return categorys;
	}

}
