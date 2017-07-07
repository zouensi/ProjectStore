package com.zouensi.dao.impl;


import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.zouensi.dao.UserDao;
import com.zouensi.domain.User;
import com.zouensi.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao{
	private QueryRunner qr = new QueryRunner(DataSourceUtils.getDs());
	/**
	 * 注册信息dao实现方法
	 */
	@Override
	public int registerInfo(User user) throws SQLException {
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = new Object[]{user.getUid(),user.getUsername(),user.getPassword(),
									user.getName(),user.getEmail(),user.getTelephone(),
									user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
		int state = qr.update(sql, obj);
		return state;
	}
	@Override
	public User getUser(String code) throws SQLException {
		String sql = "select * from user where code = ? limit 1";
		User user = qr.query(sql, new BeanHandler<>(User.class),code);
		return user;
	}
	@Override
	public int updateUser(User user) throws SQLException {
		String sql = "update user set username=?,password=?,name=?,email=?,birthday=?,sex=?,state=?";
		Object[] objs = new Object[]{user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getBirthday(),user.getSex(),user.getState()};
		int state = qr.update(sql, objs);
		return state;
	}
	@Override
	public User loginInfo(String username, String password) throws SQLException {
		String sql = "select * from user where username = ? and password = ? limit 1";
		User user = qr.query(sql, new BeanHandler<User>(User.class),username,password);
		return user;
	}
}
