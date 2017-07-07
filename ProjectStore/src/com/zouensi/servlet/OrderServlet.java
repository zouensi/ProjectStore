package com.zouensi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author zouensi
 * @date 2017年7月7日
 * 描述:订单servlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    
    public OrderServlet() {
        super();
    }

    public String orderList(HttpServletRequest request,HttpServletResponse response) {
    	return "/WEB-INF/jsp/order_list.jsp";
    	
    }
}
