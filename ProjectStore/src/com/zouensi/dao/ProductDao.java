package com.zouensi.dao;

import java.sql.SQLException;
import java.util.List;

import com.zouensi.domain.Product;

public interface ProductDao {
	/**
	 * ��ȡһ�����͵ķ�ҳ����
	 * @param cid
	 * @param pageNumer
	 * @return
	 * @throws SQLException
	 */
	List<Product> findProductLimit(String cid,int pageNumer,int PageCount) throws SQLException;
	/**
	 * ��ȡһ�����͵�������
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
