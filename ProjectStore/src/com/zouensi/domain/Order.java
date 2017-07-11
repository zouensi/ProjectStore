package com.zouensi.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	private String oid;//orderid ���ô�
	private String ordertime;//����ʱ�䲻�ô�
	private double total;//�ܽ��ô�
	private Integer state;//״̬ ���ô�  0: δ����   1 :�Ѹ���δ����  2 �ѷ���δ�ջ�  3 ���ջ�δ����  4 ������
	private String address;//��ַ���ô�
	private String name;//�û��� �Լ���
	private String telephone;//�绰��
	private User user;//�û�
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();//�������б�
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
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total="
				+ total + ", state=" + state + ", address=" + address
				+ ", name=" + name + ", telephone=" + telephone + ", user="
				+ user + ", orderItems=" + orderItems + "]";
	}
	
}
