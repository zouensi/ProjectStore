package com.zouensi.dao;

import java.sql.SQLException;
import java.util.List;

import com.zouensi.domain.Product;

public interface ProductDao {
	/**
	 * 获取一个类型的分页条数
	 * @param cid
	 * @param pageNumer
	 * @return
	 * @throws SQLException
	 */
	List<Product> findProductLimit(String cid,int pageNumer,int PageCount) throws SQLException;
	/**
	 * 获取一个类型的总条数
	 * @param cid
	 * @return
	 * @throws SQLException 
	 */
	Long getCount(String cid) throws SQLException;
	
	/**
	 * 
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
