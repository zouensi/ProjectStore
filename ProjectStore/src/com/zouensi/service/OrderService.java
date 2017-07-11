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
	/**
	 * ����oid���Ҷ���
	 * @param oid
	 * @return
	 * @throws SQLException
	 */
	Order findOrderById(String oid) throws SQLException;
	
	/**
	 * ���¶�����Ϣ��ȷ�϶�����
	 * @param i
	 * @param address
	 * @param name
	 * @param telephone
	 * @return
	 * @throws SQLException 
	 */
	boolean updateOrder(String address, String name, String telephone,String oid) throws SQLException;

	/**
	 * ���¶���״̬
	 * @param state
	 * @throws SQLException 
	 */
	boolean updateOrderState(int state,String oid) throws SQLException;
	
}
