package com.zouensi.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zouensi.domain.Product;
import com.zouensi.factory.BeanFactory;
import com.zouensi.service.ProductService;

/**
 * 
 * @author zouensi
 * @date 2017年7月6日
 * 描述:首页请求转发
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductService service = BeanFactory.getBean(ProductService.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Product> hotProducts = service.getHotProduct();
			List<Product> newProducts= service.getNewProduct();
			request.setAttribute("hotProducts", hotProducts);
			request.setAttribute("newProducts", newProducts);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
