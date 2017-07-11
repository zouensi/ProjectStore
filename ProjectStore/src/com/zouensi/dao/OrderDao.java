package com.zouensi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.zouensi.domain.Order;
import com.zouensi.domain.OrderItem;
import com.zouensi.domain.User;

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
	/**
	 * ���Ҷ�����Ϣ
	 * @param user
	 * @param pageNumber
	 * @param pageSize
	 */
	List<Order> findOrders(User user, int pageNumber, int pageSize)  throws SQLException;
	/**
	 * ��ȡ����������
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	long getCount(User user) throws SQLException;
	
	/**
	 * ��ȡorderitem
	 * @param oid
	 * @return
	 * @throws SQLException
	 */
	 List<Map<String, Object>> getOrderItems(String oid) throws SQLException;
	 /**
	  * ����id����
	  * @param oid
	 * @throws SQLException 
	  */
	 Order findOrderByid(String oid) throws SQLException;
	 /**
	  * ���¶�����Ϣ��ȷ��������
	  * @param state
	  * @param address
	  * @param name
	  * @param telephone
	  * @return
	 * @throws SQLException 
	  */
	int updateOrder( String address, String name, String telephone,String oid) throws SQLException;
	int updateOrderState(int state, String oid) throws SQLException;

}
