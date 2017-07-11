package com.zouensi.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zouensi.domain.Order;
import com.zouensi.domain.OrderItem;

public interface OrderDao {
	/**
	 * ��Ӷ���
	 * @param order
	 * @throws SQLException 
	 */
	int addOrder(Connection conn,Order order) throws SQLException;
	/**
	 * ��Ӷ�����
	 * @param orderItem
	 * @throws SQLException 
	 */
	int addOrderItem(Connection conn,OrderItem orderItem) throws SQLException;

}
