package com.zouensi.domain;
/**
 * 
 * @author zouensi
 * @date 2017��7��9��
 * ����:���������
 */
public class CartItem {
	private Product product;//��Ʒ����
	private int count;//�������Ʒ����
	private double subTotal;//ÿһ��������С��
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * ��ȡС�ƽ��
	 * @return
	 */
	public double getSubTotal() {
		subTotal = product.getShop_price()*count;
		return subTotal;
	}
	//��ֹ�����Ա���ø÷������ж�����Ľ�����Բ��ø÷���
	/*public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}*/
	
}
