package com.zouensi.service;

import java.sql.SQLException;
import java.util.List;

import com.zouensi.domain.Product;

public interface ProductService {
	
	String findProductLimit(String cid,int pageNumber) throws SQLException;
	
	/**
	 * ��ȡ
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	Product getProduct(String pid) throws SQLException;
	
	/**
	 * ��ȡ����
	 * @return
	 * @throws SQLException
	 */
	List<Product> getHotProduct() throws SQLException;
	/**
	 * ��ȡ������Ʒ
	 * @return
	 * @throws SQLException
	 */
	List<Product> getNewProduct() throws SQLException;
}
