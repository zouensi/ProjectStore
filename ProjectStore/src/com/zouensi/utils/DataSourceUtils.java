package com.zouensi.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	private static final ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	/**
	 * 获取连接池
	 * @return
	 */
	public static ComboPooledDataSource getDs() {
		return ds;
	}
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection = local.get();
		if(connection==null) {
			try {
				connection = getDs().getConnection();
				local.set(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}
	/**
	 * 关闭连接
	 */
	public static void close() {
		Connection conn = local.get();
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			local.remove();
		}
	}
}
