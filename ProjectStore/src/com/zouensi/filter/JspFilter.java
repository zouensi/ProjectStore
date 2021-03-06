package com.zouensi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zouensi.domain.User;

/**
 * Servlet Filter implementation class JspFilter
 */
@WebFilter(urlPatterns={"/jsp/order_list.jsp","/jsp/order_info.jsp"})
public class JspFilter implements Filter {

    public JspFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null) {
			request.setAttribute("msg", "���ȵ�¼");
			request.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
