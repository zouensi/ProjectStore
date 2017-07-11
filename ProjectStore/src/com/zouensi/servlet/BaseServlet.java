package com.zouensi.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zouensi.utils.Utils;

/**
 * 
 * @author zouensi
 * @date 2017年7月6日
 * 描述:servlet基类
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BaseServlet() {
        super();
    }

    /**
     * 利用反射调用每个servlet的对应方法
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取要调用的方法的名称
		String name = request.getParameter("method");
		if(Utils.isEmpty(name)) {
			defaultMethod(request, response);
		}else {
			//谁调用对象是谁（子类调用就是子类字节码对象）
			Class<? extends BaseServlet> clazz = this.getClass();
			try {
				//获取方法对象
				Method method = clazz.getMethod(name, HttpServletRequest.class,HttpServletResponse.class);
				//调用方法同时获取返回值,返回null代表请求转发
				Object obj = method.invoke(this, request,response);//传入的这两个参数需要让具体业务方法进行调用处理
				if(obj!=null) {
					request.getRequestDispatcher(obj.toString()).forward(request, response);
				}
			} catch (Exception e) {
				showInfo(request,response,"亲，请求错误");
				e.printStackTrace();
			}
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	/**
	 * 错误页面统一跳转方法
	 * @param request
	 * @param msg
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void showInfo(HttpServletRequest request, HttpServletResponse response,String msg) throws ServletException, IOException {
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("jsp/info.jsp").forward(request, response);
	}
	
	public void defaultMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
	}

}
