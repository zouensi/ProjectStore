package com.zouensi.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author zouensi
 * @date 2017年7月6日
 * 描述:编码格式转换filter
 */
/*"/EncodeFilter"*/
@WebFilter(filterName="EncodeFilter",urlPatterns="/*")
public class EncodeFilter implements Filter {
	private static ClassLoader loader;
	
	{
		loader = this.getClass().getClassLoader();
	}
    public EncodeFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//由于提供的参数中没有提欧共的对应方法所以要强转
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		rep.setContentType("text/html;charset=utf-8");
		//获取请求的方式
		String method = req.getMethod();
		if("POST".equals(method)) {
//			System.out.println("post");
			req.setCharacterEncoding("utf-8");
			chain.doFilter(req, rep);
		}else if("GET".equals(method)) {	
			//用代理模式对req进行代理
			HttpServletRequest newReq = (HttpServletRequest) Proxy.newProxyInstance(loader, req.getClass().getInterfaces(), new MyHandler(req));
			chain.doFilter(newReq, rep);
		}else {
			chain.doFilter(request, rep);
		}
	
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	/**
	 * 
	 * @author zouensi
	 * @date 2017年7月7日
	 * 描述:动态代理处理类
	 */
	class MyHandler implements InvocationHandler {
		private Object obj;
		public MyHandler(Object obj) {
			this.obj = obj;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			//获取调用方法名称
			String name = method.getName();
			if("getParameter".equalsIgnoreCase(name)) {
				String value = (String)method.invoke(obj, args);//获取方法的返回值
				if(value!=null) {
					return new String(value.getBytes("iso8859-1"),"utf-8");
				}else {
					return null;
				}
				
			}else if("getParameterMap".equalsIgnoreCase(name)) {
				Map<String, String[]> parameterMap = (Map<String, String[]>) method.invoke(obj, args);
				for (Map.Entry<String,String[]> entry : parameterMap.entrySet()) {
					if(entry.getValue().length==1) {
						String[] value = entry.getValue();
						value[0] = new String(value[0].getBytes("iso8859-1"),"utf-8");
					}
				}
				return parameterMap;
			}else {
				return method.invoke(obj, args);
			}
			
		}
		
	}

}
