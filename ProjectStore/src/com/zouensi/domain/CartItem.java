package com.zouensi.domain;
/**
 * 
 * @author zouensi
 * @date 2017年7月9日
 * 描述:购物项对象
 */
public class CartItem {
	private Product product;//商品对象
	private int count;//购买的商品数量
	private double subTotal;//每一个购物项小计
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
	 * 获取小计金额
	 * @return
	 */
	public double getSubTotal() {
		subTotal = product.getShop_price()*count;
		return subTotal;
	}
	//防止外界人员调用该方法进行恶意更改金额所以不用该方法
	/*public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}*/
	
}
