package com.zouensi.service;

import java.sql.SQLException;

import com.zouensi.domain.Order;
import com.zouensi.domain.PageBean;
import com.zouensi.domain.User;

public interface OrderService {
	/**
	 * 添加订单
	 * @param order
	 * @return
	 */
	boolean addOrder(Order order);

	/**
	 * 查找订单
	 * @param user
	 * @param pageNumber
	 * @param pageSize
	 * @throws SQLException 
	 */
	PageBean findOrders(User user, int pageNumber, int pageSize) throws SQLException;
	/**
	 * 根据oid查找订单
	 * @param oid
	 * @return
	 * @throws SQLException
	 */
	Order findOrderById(String oid) throws SQLException;
	
	/**
	 * 更新订单信息（确认订单）
	 * @param i
	 * @param address
	 * @param name
	 * @param telephone
	 * @return
	 * @throws SQLException 
	 */
	boolean updateOrder(String address, String name, String telephone,String oid) throws SQLException;

	/**
	 * 更新订单状态
	 * @param state
	 * @throws SQLException 
	 */
	boolean updateOrderState(int state,String oid) throws SQLException;
	
}
