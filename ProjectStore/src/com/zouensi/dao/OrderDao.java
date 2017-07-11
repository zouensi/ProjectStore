package com.zouensi.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zouensi.domain.Order;
import com.zouensi.domain.OrderItem;

public interface OrderDao {
	/**
	 * 添加订单
	 * @param order
	 * @throws SQLException 
	 */
	int addOrder(Connection conn,Order order) throws SQLException;
	/**
	 * 添加订单项
	 * @param orderItem
	 * @throws SQLException 
	 */
	int addOrderItem(Connection conn,OrderItem orderItem) throws SQLException;

}
