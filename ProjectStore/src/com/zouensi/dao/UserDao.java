package com.zouensi.dao;

import java.sql.SQLException;

import com.zouensi.domain.User;

public interface  UserDao {
	/**
	 * ע��
	 * @return
	 * @throws SQLException 
	 */
	int registerInfo(User user) throws SQLException;
	/**
	 * ���ݼ���code������Ϣ
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	User getUser(String code) throws SQLException;
	
	/**
	 * ���ݼ���code������Ϣ
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	int updateUser(User user) throws SQLException;
	/**
	 * �û���¼
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	User loginInfo(String username, String password) throws SQLException;
}
