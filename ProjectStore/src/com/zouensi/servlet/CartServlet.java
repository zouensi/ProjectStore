package com.zouensi.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zouensi.domain.Cart;
import com.zouensi.domain.CartItem;
import com.zouensi.domain.Product;
import com.zouensi.factory.BeanFactory;
import com.zouensi.service.ProductService;

/**
 * 
 * @author zouensi
 * @date 2017��7��7��
 * ����:���ﳵservlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private ProductService pservice = BeanFactory.getBean(ProductService.class);
    /**
     * @see BaseServlet#BaseServlet()
     */
    public CartServlet() {
        super();
    }

    public String cart(HttpServletRequest request,HttpServletResponse response) {
    	return "/jsp/cart.jsp";
    }
    /**
     * ���ﳵ�����Ʒ
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String addCartItem(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	Cart cart = (Cart) session.getAttribute("cart");
    	//��ȡpid
    	String pid = request.getParameter("pid");
    	Product product;
		try {
			product = pservice.getProduct(pid);
			int count = Integer.valueOf(request.getParameter("count"));
			System.out.println(count);
	    	CartItem cartItem = new CartItem();
	    	cartItem.setProduct(product);
	    	cartItem.setCount(count);
	    	if(cart==null) {
	    		cart = new Cart();
	    		cart.add(cartItem);
	    		session.setAttribute("cart", cart);
	    	}else {
	    		cart.add(cartItem);
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
			showInfo(request, response, "��,������Ʒ��Ϣ����"+e.toString());
			return null;
		}
    	//ͨ����Ӧ��jessionid����ȥ�������ڿͻ��ˣ�
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
    	return null;
    }
    
    /**
     * ɾ��ĳһ��
     * @param request
     * @param response
     * @return
     */
    public String remove(HttpServletRequest request,HttpServletResponse response) {
    	HttpSession session = request.getSession();
    	Cart cart = (Cart)session.getAttribute("cart");
    	String pid = request.getParameter("pid");
    	cart.remove(pid);
    	session.setAttribute("cart", cart);
    	return "/jsp/cart.jsp";
    }
    
    /**
     * ɾ��������
     * @param request
     * @param response
     * @return
     */
    public String removeAll(HttpServletRequest request,HttpServletResponse response) {
    	HttpSession session = request.getSession();
    	Cart cart = (Cart)session.getAttribute("cart");
    	cart.removeAll();
    	return "/jsp/cart.jsp";
    }
    
    /**
     * Ĭ�Ϸ���
     */
    @Override
    public void defaultMethod(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/jsp/cart.jsp").forward(request, response); 
    }

}
