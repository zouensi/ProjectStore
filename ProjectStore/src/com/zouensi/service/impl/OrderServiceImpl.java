package com.zouensi.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.DbUtils;

import com.zouensi.dao.OrderDao;
import com.zouensi.domain.Order;
import com.zouensi.domain.OrderItem;
import com.zouensi.domain.PageBean;
import com.zouensi.domain.Product;
import com.zouensi.domain.User;
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
	/**
	 * 订单分页信息
	 */
	@Override
	public PageBean findOrders(User user, int pageNumber, int pageSize) throws SQLException {
		int totalCount = (int) dao.getCount(user);//总记录数
		int totalPage = 0;//总页数
		if(totalCount%pageSize==0) {
			totalPage = totalCount/pageNumber;
		}else {
			totalPage = totalCount/pageNumber + 1;
		}
		List<Order> orders = dao.findOrders(user,pageNumber,pageSize);
		for (Order order : orders) {
			List<OrderItem> orderItems = order.getOrderItems();
			List<Map<String, Object>> maps = dao.getOrderItems(order.getOid());
			//封装orderItem
			for (Map<String, Object> map : maps) {
				OrderItem orderItem = new OrderItem();
				Product product = new Product();
				try {
					BeanUtils.populate(orderItem, map);
					BeanUtils.populate(product, map);
					orderItem.setProduct(product);
					orderItem.setOrder(order);
					orderItems.add(orderItem);
				}  catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		}
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setList(orders);
		pageBean.setPageNumber(pageNumber);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}
	@Override
	public Order findOrderById(String oid) throws SQLException {
		Order order = dao.findOrderByid(oid);
		List<OrderItem> orderItems = order.getOrderItems();
		List<Map<String, Object>> maps = dao.getOrderItems(order.getOid());
		//封装orderItem
		for (Map<String, Object> map : maps) {
			OrderItem orderItem = new OrderItem();
			Product product = new Product();
			try {
				BeanUtils.populate(orderItem, map);
				BeanUtils.populate(product, map);
				orderItem.setProduct(product);
				orderItem.setOrder(order);
				orderItems.add(orderItem);
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return order;
	}
	@Override
	public boolean updateOrder( String address, String name,
			String telephone,String oid) throws SQLException {
		int status = dao.updateOrder(  address,  name,telephone,oid) ;
		if(status>0) {
			return true;
		}
		return false;
	}
	@Override
	public boolean updateOrderState(int state, String oid) throws SQLException {
		int status = dao.updateOrderState(state,oid);
		if(status>0) {
			return true;
		}
		return false;
	}

}
