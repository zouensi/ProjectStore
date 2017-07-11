package com.zouensi.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.zouensi.dao.ProductDao;
import com.zouensi.domain.PageBean;
import com.zouensi.domain.Product;
import com.zouensi.factory.BeanFactory;
import com.zouensi.service.ProductService;

public class ProductServiceImpl implements ProductService {
	private ProductDao dao = BeanFactory.getBean(ProductDao.class);
	/**
	 * 获取分页信息
	 */
	@Override
	public String findProductLimit(String cid,int pageNumber) throws SQLException {
		long totalCount = dao.getCount(cid);//总记录数
		int pageCount = 12;//一页记录数
		List<Product> products = dao.findProductLimit(cid,pageNumber,pageCount);
		int totalSize = 0;//总页数
		if(totalCount%pageCount==0) {
			totalSize = (int)totalCount/pageCount;
		}else {
			totalSize = (int)totalCount/pageCount+1;
		}
		PageBean pageBean = new PageBean();
		pageBean.setList(products);
		pageBean.setPageNumber(pageNumber);
		pageBean.setPageSize(pageCount);
		pageBean.setTotalPage(totalSize);
		pageBean.setTotalCount((int)totalCount);
		return JSON.toJSONString(pageBean);
	}
	@Override
	public Product getProduct(String pid) throws SQLException {
		Product product = dao.getProduct(pid);
		return product;
	}
	@Override
	public List<Product> getHotProduct() throws SQLException {
		List<Product> hotProduct = dao.getHotProduct();
		return hotProduct;
	}
	@Override
	public List<Product> getNewProduct() throws SQLException {
		List<Product> newProduct = dao.getNewProduct();
		return newProduct;
	}

}
