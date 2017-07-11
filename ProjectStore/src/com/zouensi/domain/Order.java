package com.zouensi.domain;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	private String oid;//orderid 不用传
	private String ordertime;//创建时间不用传
	private double total;//总金额不用传
	private Integer state;//状态 不用传
	private String address;//地址不用传
	private String name;//用户名 自己查
	private String telephone;//电话号
	private User user;//用户
	private List<OrderItem> orderItems;//订单项列表
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
