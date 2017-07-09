package com.zouensi.service.impl;

import java.sql.SQLException;

import com.zouensi.dao.UserDao;
import com.zouensi.domain.User;
import com.zouensi.factory.BeanFactory;
import com.zouensi.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao dao = BeanFactory.getBean(UserDao.class);
	/**
	 * 注册service实现方法
	 */
	@Override
	public boolean registerInfo(User user) throws SQLException {
		int state = dao.registerInfo(user);
		if(state>0) {
			return true;
		}
		return false;
	}
	@Override
	public User getUser(String code) throws SQLException {
		User user = dao.getUser(code);
		return user;
	}
	@Override
	public boolean updateUser(User user) throws SQLException {
		int state = dao.updateUser(user);
		if(state>0) {
			return true;
		}
		return false;
	}
	@Override
	public User loginInfo(String username, String password) throws SQLException {
		User user = dao.loginInfo(username,password);
		return user;
	}

}
