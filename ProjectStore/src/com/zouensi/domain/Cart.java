package com.zouensi.domain;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 
 * @author zouensi
 * @date 2017年7月9日
 * 描述:购物车对象
 */
public class Cart {
	private double total;//购物车物品总金额
	//使用LinkedHashMap的好处是在于（1）查找方便,效率高（2）Linked有序
	private Map<String,CartItem> map = new LinkedHashMap<String, CartItem>();//购物项集合
	public double getTotal() {
		return total;
	}
	//为了防止恶意修改不提供该方法
	/*public void setTotal(double total) {
		this.total = total;
	}*/
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	
	//购物车主要有三个操作add(CartItem),remove(key),removeAll();
	
	/**
	 * 添加购物项
	 * @param cartItem
	 */
	public void add(CartItem cartItem) {
		//如果购物项不存在map中直接填加
		CartItem tempCart = map.get(cartItem.getProduct().getPid());
		String key = cartItem.getProduct().getPid();
		if(tempCart==null) {
			map.put(key, cartItem);
			
		}else {
			//如果存在就在原有购物项基础上添加数量
			tempCart.setCount(tempCart.getCount()+cartItem.getCount());
			map.put(key, tempCart);
		}
		total = total+cartItem.getSubTotal();
	}
	
	/**
	 * 根据键删除购物项
	 * @param key
	 */
	public void remove(String key) {
		CartItem cartItem = map.remove(key);
		total = total-cartItem.getSubTotal();
	}
	
	/**
	 * 清楚购物车
	 */
	public void removeAll() {
		map.clear();
		total=0;
	}
	
}
