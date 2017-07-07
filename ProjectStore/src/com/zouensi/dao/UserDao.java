package com.zouensi.dao;

import java.sql.SQLException;

import com.zouensi.domain.User;

public interface  UserDao {
	/**
	 * 注册
	 * @return
	 * @throws SQLException 
	 */
	int registerInfo(User user) throws SQLException;
	/**
	 * 根据激活code查找信息
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	User getUser(String code) throws SQLException;
	
	/**
	 * 根据激活code查找信息
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	int updateUser(User user) throws SQLException;
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	User loginInfo(String username, String password) throws SQLException;
}
