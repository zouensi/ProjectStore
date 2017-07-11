package com.zouensi.domain;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 
 * @author zouensi
 * @date 2017��7��9��
 * ����:���ﳵ����
 */
public class Cart {
	private double total;//���ﳵ��Ʒ�ܽ��
	//ʹ��LinkedHashMap�ĺô������ڣ�1�����ҷ���,Ч�ʸߣ�2��Linked����
	private Map<String,CartItem> map = new LinkedHashMap<String, CartItem>();//�������
	public double getTotal() {
		return total;
	}
	//Ϊ�˷�ֹ�����޸Ĳ��ṩ�÷���
	/*public void setTotal(double total) {
		this.total = total;
	}*/
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	
	//���ﳵ��Ҫ����������add(CartItem),remove(key),removeAll();
	
	/**
	 * ��ӹ�����
	 * @param cartItem
	 */
	public void add(CartItem cartItem) {
		//������������map��ֱ�����
		CartItem tempCart = map.get(cartItem.getProduct().getPid());
		String key = cartItem.getProduct().getPid();
		if(tempCart==null) {
			map.put(key, cartItem);
			
		}else {
			//������ھ���ԭ�й�����������������
			tempCart.setCount(tempCart.getCount()+cartItem.getCount());
			map.put(key, tempCart);
		}
		total = total+cartItem.getSubTotal();
	}
	
	/**
	 * ���ݼ�ɾ��������
	 * @param key
	 */
	public void remove(String key) {
		CartItem cartItem = map.remove(key);
		total = total-cartItem.getSubTotal();
	}
	
	/**
	 * ������ﳵ
	 */
	public void removeAll() {
		map.clear();
		total=0;
	}
	
}
