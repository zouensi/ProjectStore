package com.zouensi.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.zouensi.factory.BeanFactory;
import com.zouensi.service.CategoryService;

/**
 * 
 * @author zouensi
 * @date 2017年7月8日
 * 描述:分类servlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CategoryService service = BeanFactory.getBean(CategoryService.class);
    public CategoryServlet() {
        super();
    }
    
    /**
     * 获取类型数据
     * @param request
     * @param response
     * @return
     */
    public String getCategory(HttpServletRequest request,HttpServletResponse response) {
    	try {
    		String info= service.getCategory();
			response.getWriter().print(info);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
}
