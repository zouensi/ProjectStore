package com.zouensi.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.zouensi.cache.CategoryCache;
import com.zouensi.dao.CategoryDao;
import com.zouensi.domain.Category;
import com.zouensi.factory.BeanFactory;
import com.zouensi.service.CategoryService;
import com.zouensi.utils.Utils;

public class CategoryServiceImpl implements CategoryService {
	private CategoryDao dao = BeanFactory.getBean( CategoryDao.class);
	private CategoryCache cache = BeanFactory.getBean( CategoryCache.class);
	@Override
	public String getCategory() throws SQLException {
		//�ӻ����л�ȡ��Ϣ
		String info = cache.getCategory();
		//���������û�оʹ����ݿ��л�ȡ
		if(Utils.isEmpty(info)) {
			List<Category> categorys = dao.getCategory();
			info = JSON.toJSONString(categorys);
			//��ӵ�������
			cache.setCategory(info);
		}
		return info;
	}

	

}
