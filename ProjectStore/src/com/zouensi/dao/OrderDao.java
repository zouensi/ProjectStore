package com.zouensi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.zouensi.domain.Order;
import com.zouensi.domain.OrderItem;
import com.zouensi.domain.User;

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
	/**
	 * 查找订单信息
	 * @param user
	 * @param pageNumber
	 * @param pageSize
	 */
	List<Order> findOrders(User user, int pageNumber, int pageSize)  throws SQLException;
	/**
	 * 获取订单总条数
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	long getCount(User user) throws SQLException;

}
