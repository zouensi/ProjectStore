package com.zouensi.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.zouensi.dao.OrderDao;
import com.zouensi.domain.Order;
import com.zouensi.domain.OrderItem;
import com.zouensi.factory.BeanFactory;
import com.zouensi.service.OrderService;
import com.zouensi.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {
	private OrderDao dao = BeanFactory.getBean(OrderDao.class);
	@Override
	public boolean addOrder(Order order)  {
		Connection conn = DataSourceUtils.getConnection();
		try {
			conn.setAutoCommit(false);
			dao.addOrder(conn,order);
			List<OrderItem> orderItems = order.getOrderItems();
			for (OrderItem orderItem : orderItems) {
				dao.addOrderItem(conn, orderItem);
			}
			DbUtils.commitAndClose(conn);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DbUtils.rollbackAndClose(conn);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return false;
	}

}
