package com.zouensi.service;

import java.sql.SQLException;
import java.util.List;

import com.zouensi.domain.Product;

public interface ProductService {
	
	String findProductLimit(String cid,int pageNumber) throws SQLException;
	
	/**
	 * 获取
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	Product getProduct(String pid) throws SQLException;
	
	/**
	 * 获取最热
	 * @return
	 * @throws SQLException
	 */
	List<Product> getHotProduct() throws SQLException;
	/**
	 * 获取最新商品
	 * @return
	 * @throws SQLException
	 */
	List<Product> getNewProduct() throws SQLException;
}
