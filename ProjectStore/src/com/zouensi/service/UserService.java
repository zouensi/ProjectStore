package com.zouensi.service;

import java.sql.SQLException;

import com.zouensi.domain.User;

public interface UserService {
	/**
	 * 注册
	 * @return
	 * @throws SQLException 
	 */
	boolean registerInfo(User user) throws SQLException;
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
	boolean updateUser(User user) throws SQLException;
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @throws SQLException 
	 */
	User loginInfo(String username, String password) throws SQLException;
}
