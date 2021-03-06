package com.zouensi.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zouensi.dao.OrderDao;
import com.zouensi.domain.Order;
import com.zouensi.domain.OrderItem;
import com.zouensi.domain.User;
import com.zouensi.utils.QueryRunnerUtils;

public class OrderDaoImpl implements OrderDao {
	private QueryRunner qrNods = QueryRunnerUtils.getQueryRunner();
	private QueryRunner qr = QueryRunnerUtils.getQueryRunnerWithDs();
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

	@Override
	public List<Order> findOrders(User user, int pageNumber, int pageSize) throws SQLException {
		String sql = "select * from orders where uid = ? limit ?,? ";
		int start = (pageSize-1)*pageNumber;
		List<Order> orders = qr.query(sql, new BeanListHandler<Order>(Order.class),user.getUid(),start,pageNumber);
		return orders;
	}

	@Override
	public long getCount(User user) throws SQLException {
		String sql = "select count(*) from orders where uid = ?";
		long count = (Long)qr.query(sql, new ScalarHandler(),user.getUid());
		return count;
	}

	@Override
	public  List<Map<String, Object>> getOrderItems(String oid) throws SQLException {
		String sql = "select * from orderitem o ,product p where o.oid = ? and o.pid = p.pid";
		 List<Map<String, Object>> maps = qr.query( sql, new MapListHandler() ,oid);
		return maps;
	}

	@Override
	public Order findOrderByid(String oid) throws SQLException {
		String sql = "select * from orders where oid = ?";
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class),oid);
		return order;
	}

	@Override
	public int updateOrder(String address, String name,
			String telephone,String oid) throws SQLException {
		String sql = "update orders set address=?,name=?,telephone=? where oid = ?";
		int status = qr.update(sql,address,name,telephone,oid);
		return status;
	}

	@Override
	public int updateOrderState(int state, String oid) throws SQLException {
		String sql = "update orders set state=? where oid = ?";
		qr.update(sql,state,oid);
		return 0;
	}
	
	
	
	
}
