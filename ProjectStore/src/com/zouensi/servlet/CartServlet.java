package com.zouensi.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author zouensi
 * @date 2017年7月7日
 * 描述:购物车servlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public CartServlet() {
        super();
    }

    public String cart(HttpServletRequest request,HttpServletResponse response) {
    	return "/WEB-INF/jsp/cart.jsp";
    }

}
