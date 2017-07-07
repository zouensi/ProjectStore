package com.zouensi.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zouensi.domain.User;
import com.zouensi.service.UserService;
import com.zouensi.service.impl.UserServiceImpl;
/**
 * 
 * @author zouensi
 * @date 2017年7月7日
 * 描述:自动登录过滤器
 */
@WebFilter("/*")
public class AutomaticLogin implements Filter {
	private static UserService service = new UserServiceImpl();
    public AutomaticLogin() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("user");//查看是否已经有会话
		if(obj!=null) {//有直接放行
			chain.doFilter(request, response);
			return;
		}
		String username = null;
		String password = null;
		Cookie[] cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			if("username".equals(cookie.getName())) {
				username = cookie.getValue();
			}
			if("password".equals(cookie.getName())) {
				password = cookie.getValue();
			}
		}
		try {
			if(username!=null&&password!=null) {
				User user = service.loginInfo(username, password);
				if(user!=null) {
					session.setAttribute("user", user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
