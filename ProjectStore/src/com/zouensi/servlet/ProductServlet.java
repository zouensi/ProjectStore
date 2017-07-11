package com.zouensi.servlet;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zouensi.domain.Product;
import com.zouensi.factory.BeanFactory;
import com.zouensi.service.ProductService;


@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private ProductService service = BeanFactory.getBean(ProductService.class);
    public ProductServlet() {
        super();
    }
    /**
     * ����cid�Ҷ�Ӧ����Ʒ��Ϣ
     * @param request
     * @param response
     * @return
     */
    public String productForword(HttpServletRequest request,HttpServletResponse response) {
    	request.setAttribute("cid", request.getParameter("cid"));
    	return "/jsp/product_list.jsp";
    }
    /**
     * ��ȡ��ҳ����
     * @param request
     * @param response
     * @return
     */
    public String findProductLimit(HttpServletRequest request,HttpServletResponse response) {
    	String cid = request.getParameter("cid");
    	String pn = request.getParameter("pageNumber");
    	String info = "";
    	
    	try {
    		info  = service.findProductLimit(cid, Integer.valueOf(pn));
    		System.out.println(info);
    		response.getWriter().print(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * ��ȡ��Ʒ��Ϣ����չʾ
     * @param request
     * @param response
     * @return
     */
    public String showProductInfo(HttpServletRequest request,HttpServletResponse response) {
    	String pid = request.getParameter("pid");
    	
    	try {
			Product product = service.getProduct(pid);
			System.out.println(product);
			request.setAttribute("product", product);;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return "/jsp/product_info.jsp";
    }
    
}
