package com.zouensi.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.zouensi.domain.User;
import com.zouensi.service.UserService;
import com.zouensi.service.impl.UserServiceImpl;
import com.zouensi.threadhandler.EmailRunnable;
import com.zouensi.threadhandler.EmaliThreadPool;
import com.zouensi.utils.UUIDUtils;
/**
 * 
 * @author zouensi
 * @date 2017年7月7日
 * 描述:用户servlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();
    public UserServlet() {
        super();
    }
    /**
     * 注册页面跳转
     * @return
     */
    public String register(HttpServletRequest request,HttpServletResponse response) {
    	
		return "/WEB-INF/jsp/register.jsp";
    }
    
    /**
     * 登录页面跳转
     * @return
     */
    public String login(HttpServletRequest request,HttpServletResponse response) {
    	
		return "/WEB-INF/jsp/login.jsp";
    }
    /**
     *退出登录 
     */
    public String quit(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	HttpSession session = request.getSession();
    	session.setAttribute("user", null);
    	session.invalidate();
    	//由于自动登录关系，浏览器保存了cookie所以cookie也要移除
    	Cookie[] cookies = request.getCookies();
    	for (Cookie cookie : cookies) {
    		if("username".equals(cookie.getName())) {
    			cookie.setMaxAge(0);//age设置为0立即失效
    			response.addCookie(cookie);
    		}
    		if("password".equals(cookie.getName())) {
    			cookie.setMaxAge(0);
    			response.addCookie(cookie);
    		}
		}
    	response.sendRedirect(request.getContextPath());
		return null;
    }
    /**
     * 注册信息
     * @return
     * @throws IOException 
     * @throws ServletException 
     */
    public String registerInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	Map<String, String[]> parameterMap = request.getParameterMap();
    	//获取用户输入的验证码
    	String userYum = request.getParameter("userYum");
    	//获取session中的验证码
    	HttpSession session = request.getSession();
    	String sessionYum = (String) session.getAttribute("yum");
    	if(userYum==null||sessionYum==null||sessionYum.equalsIgnoreCase(userYum)) {
    		request.setAttribute("msg","验证码输入错误");
    		session.setAttribute("yum", "");//让用户必重新输入验证码
    		return "/WEB-INF/jsp/register.jsp?method=registerInfo";
    	}
    	User user = new User();
    	try {
			BeanUtils.populate(user, parameterMap);
			//获取主键
			String uid = UUIDUtils.getUUID();
			user.setUid(uid);
			//设置激活状态,还没有激活所以为0
			user.setState(0);
			//获取激活code
			String code =  UUIDUtils.getUUID()+ UUIDUtils.getUUID();
			user.setCode(code);
			boolean state = service.registerInfo(user);
			if(state) {
				request.setAttribute("msg", "亲,注册成功,请根据邮件进行激活");
				// 异步发邮件
				String emailMsg="亲,激活邮件<a href=http://localhost:8081/ProjectStore/UserServlet?method=activate&code="
						+user.getCode()+">点击激活</a>";
				Runnable runnable = new EmailRunnable(user.getEmail(), emailMsg);
				ThreadPoolExecutor pool = EmaliThreadPool.getPool();
				pool.execute(runnable);
			}
		} catch (Exception e) {
			showInfo(request, response, "亲,注册失败,请重新注册!");
			e.printStackTrace();
		}
    	session.setAttribute("yum", "");
    	//成功跳转到info页面提示
		return "/WEB-INF/jsp/info.jsp";
    }
    
    public String activate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	//获取激活code
    	String code = request.getParameter("code");
    	try {
			User user = service.getUser(code);
			if(user==null) {
				showInfo(request, response, "亲,激活邮件过期,请重新激活!");
				return null;
			}else {
				user.setState(1);
				boolean state = service.updateUser(user);
				if(state) {
					request.setAttribute("msg", "亲,激活成功,请进行登录");
					return "/WEB-INF/jsp/login.jsp";
				}else {
					request.setAttribute("msg", "亲,激活失败!");
				}
			}
		} catch (SQLException e) {
			showInfo(request, response, "亲,激活失败!");
			e.printStackTrace();
		}
    	return "/WEB-INF/jsp/info.jsp";
    }
    /**
     * 用户登录
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String loginInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	String url = "";
    	//获取用户输入的验证码
    	String userYum = request.getParameter("userYum");
    	//获取session中的验证码
    	HttpSession session = request.getSession();
    	String sessionYum = (String) session.getAttribute("yzm");
    	session.setAttribute("yzm", "");//让用户必重新输入验证码
    	if(userYum==null||sessionYum==null||!sessionYum.equalsIgnoreCase(userYum)) {
    		request.setAttribute("errorMsg","验证码输入错误");
    		return "/WEB-INF/jsp/login.jsp?method=loginInfo";
    	}
    	String zdLogin = request.getParameter("zdLogin");
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	try {
			User user = service.loginInfo(username,password);
			if(user!=null) {
				if(user.getState()==null||user.getState()==0) {
					//还没有激活
					request.setAttribute("errorMsg", "亲,还有激活,请先激活账号!");
					url = "/WEB-INF/jsp/login.jsp?method=loginInfo";
				}else {
					if("1".equals(zdLogin)) {
						Cookie cookieUsername = new Cookie("username",user.getUsername());
						cookieUsername.setMaxAge(60*60*24*7);
						Cookie cookiePassword = new Cookie("password",user.getPassword());
						cookiePassword.setMaxAge(60*60*24*7);
						//天加cookie
						response.addCookie(cookieUsername);
						response.addCookie(cookiePassword);
					}
					//登录成功
					session.setAttribute("user", user);
					response.sendRedirect(request.getContextPath());
					//返回null代表是请求转发
					return null;
				}
				
			}else {
				//用户不存在
				request.setAttribute("errorMsg", "亲,用户名或者密码不正确!");
				url = "/WEB-INF/jsp/login.jsp?method=loginInfo";
			}
		} catch (SQLException e) {
			showInfo(request, response, "亲,登录失败,请重新登录");
			e.printStackTrace();
		}
    	return url;
    }
}
