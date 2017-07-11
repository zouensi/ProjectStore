package com.zouensi.service;

import java.sql.SQLException;

import com.zouensi.domain.Order;
import com.zouensi.domain.PageBean;
import com.zouensi.domain.User;

public interface OrderService {
	/**
	 * ��Ӷ���
	 * @param order
	 * @return
	 */
	boolean addOrder(Order order);

	/**
	 * ���Ҷ���
	 * @param user
	 * @param pageNumber
	 * @param pageSize
	 * @throws SQLException 
	 */
	PageBean findOrders(User user, int pageNumber, int pageSize) throws SQLException;
	
}
