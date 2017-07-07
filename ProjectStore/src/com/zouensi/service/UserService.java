package com.zouensi.service;

import java.sql.SQLException;

import com.zouensi.domain.User;

public interface UserService {
	/**
	 * ע��
	 * @return
	 * @throws SQLException 
	 */
	boolean registerInfo(User user) throws SQLException;
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
	boolean updateUser(User user) throws SQLException;
	/**
	 * �û���¼
	 * @param username
	 * @param password
	 * @throws SQLException 
	 */
	User loginInfo(String username, String password) throws SQLException;
}
