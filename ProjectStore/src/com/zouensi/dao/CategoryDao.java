package com.zouensi.dao;

import java.sql.SQLException;
import java.util.List;

import com.zouensi.domain.Category;

public interface CategoryDao {
	/**
	 * ��ȡ����������Ϣ�ӿ�
	 * @throws SQLException 
	 */
	List<Category> getCategory() throws SQLException;
}
