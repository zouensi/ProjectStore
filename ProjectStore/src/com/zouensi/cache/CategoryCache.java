package com.zouensi.cache;

import java.sql.SQLException;


public interface CategoryCache {
	/**
	 * ��ȡ����������Ϣ�ӿ�
	 * @throws SQLException 
	 */
	String getCategory() ;
	/**
	 * ��������ӿ�
	 */
	void setCategory(String info);

}
