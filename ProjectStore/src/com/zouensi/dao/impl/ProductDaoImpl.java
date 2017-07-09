package com.zouensi.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zouensi.dao.ProductDao;
import com.zouensi.domain.Product;
import com.zouensi.utils.QueryRunnerUtils;

public class ProductDaoImpl implements ProductDao {
	private QueryRunner qr = QueryRunnerUtils.getQueryRunnerWithDs();
	@Override
	public List<Product> findProductLimit(String cid,int pageNumber,int pageCount) throws SQLException {
		int size = (pageNumber-1)*pageCount;
		String sql = "select * from product where cid = ? limit ?,?";
		List<Product> products = qr.query(sql, new BeanListHandler<Product>(Product.class),cid,size,pageCount);
		return products;
	}
	@Override
	public Long getCount(String cid) throws SQLException {
		String sql = "select count(*) from product where cid = ?";
		Long count = (Long)qr.query(sql, new ScalarHandler(),cid);
		if(count==null) {
			count = new Long(0);
		}
		return count;
	}
	@Override
	public Product getProduct(String pid) throws SQLException {
		String sql = "select * from product where pid =?";
		Product product = qr.query(sql, new BeanHandler<Product>(Product.class),pid);
		return product;
	}
	@Override
	public List<Product> getHotProduct() throws SQLException {
		String sql ="select * from product where is_hot = 0  order by pdate desc limit 0,9";
		List<Product> products = qr.query(sql, new BeanListHandler<>(Product.class));
		return products;
	}
	@Override
	public List<Product> getNewProduct() throws SQLException {
		String sql ="select * from product where pflag = 0 order by pdate limit 0,9";
		List<Product> products = qr.query(sql, new BeanListHandler<>(Product.class));
		return products;
	}

}
