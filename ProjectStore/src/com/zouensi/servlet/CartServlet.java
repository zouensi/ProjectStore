package com.zouensi.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author zouensi
 * @date 2017��7��7��
 * ����:���ﳵservlet
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
