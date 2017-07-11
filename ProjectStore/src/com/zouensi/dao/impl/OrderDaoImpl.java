package com.zouensi.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.zouensi.dao.OrderDao;
import com.zouensi.domain.Order;
import com.zouensi.domain.OrderItem;
import com.zouensi.utils.QueryRunnerUtils;

public class OrderDaoImpl implements OrderDao {
	private QueryRunner qrNods = QueryRunnerUtils.getQueryRunner();
	@Override
	public int addOrder(Connection conn,Order order) throws SQLException {
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		Object[] obj = new Object[] {order.getOid(),order.getOrdertime(),order.getTotal()
				 ,order.getState(),order.getAddress(),order.getName(),order.getTelephone()
				 ,order.getUser().getUid()};
		int state = qrNods.update(conn, sql,obj);
		return state;
	}

	@Override
	public int addOrderItem(Connection conn ,OrderItem orderItem) throws SQLException {
		String sql = "insert into orderitem values(?,?,?,?,?)";
		Object[] obj = new Object[] {orderItem.getItemid(),orderItem.getCount(),orderItem.getSubtotal(),
				orderItem.getProduct().getPid(),orderItem.getOrder().getOid()};
		int state = qrNods.update(conn, sql,obj);
		return state;
	}

}
