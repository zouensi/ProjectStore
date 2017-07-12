package com.zouensi.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zouensi.domain.Cart;
import com.zouensi.domain.CartItem;
import com.zouensi.domain.Order;
import com.zouensi.domain.OrderItem;
import com.zouensi.domain.PageBean;
import com.zouensi.domain.User;
import com.zouensi.factory.BeanFactory;
import com.zouensi.service.OrderService;
import com.zouensi.utils.PaymentUtil;
import com.zouensi.utils.UUIDUtils;

/**
 * 
 * @author zouensi
 * @date 2017年7月7日
 * 描述:订单servlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private OrderService service = BeanFactory.getBean(OrderService.class);
    
    public OrderServlet() {
        super();
    }

    public String orderList(HttpServletRequest request,HttpServletResponse response) {
    	return "/jsp/order_list.jsp";
    	
    }
    
    /**
     * 添加订单
     * @param request
     * @param response
     * @return
     * @throws IOException 
     * @throws ServletException 
     */
    public String addOrder(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
    	if(user==null) {
    		request.setAttribute("errorMsg", "请进行登录");
    		return "/jsp/cart.jsp";
    	}else {
    		//封装order
    		Cart cart = (Cart) session.getAttribute("cart");
    		String oid = UUIDUtils.getUUID();
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String ordertime = format.format(new Date());
    		double total = cart.getTotal();
    		int state = 0;
    		Order order = new Order();
    		order.setOid(oid);
    		order.setOrdertime(ordertime);
    		order.setTotal(total);
    		order.setState(state);
    		order.setName(user.getName());
    		order.setUser(user);
    		List<OrderItem> list = new ArrayList<OrderItem>();
    		order.setOrderItems(list);
    		//从购物车中获取购物项集合进行遍历
    		Map<String, CartItem> map = cart.getMap();
    		for(String key : map.keySet()) {
    			CartItem cartItem = map.get(key);
    			OrderItem orderItem = new OrderItem();
    			orderItem.setCount(cartItem.getCount());
    			orderItem.setItemid(UUIDUtils.getUUID());
    			orderItem.setOrder(order);
    			orderItem.setProduct(cartItem.getProduct());
    			orderItem.setSubtotal(cartItem.getSubTotal());
    			list.add(orderItem);
    		}
    		boolean b = service.addOrder(order);
    		if(b) {
    			request.setAttribute("order", order);
    			//订单添加成功移除session中的信息
    			session.removeAttribute("cart");
    		}else {
    			showInfo(request, response, "亲,生成订单失败");
    			return null;
    		}
    		return "/jsp/order_info.jsp";
    	}
    }
    /**
     * 查找当前用户所有订单
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findOrders(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	//获取用户信息
    	HttpSession session = request.getSession();
    	User user = (User)session.getAttribute("user");
    	if(user==null) {
    		showInfo(request, response, "亲,请先登录在进行订单查询操作!");
			return null;
    	}
    	int pageNumber = 4;
    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    	try {
			PageBean pageBean = service.findOrders(user,pageNumber,pageSize);
//			System.out.println(pageBean.toString());
			request.setAttribute("pageBean", pageBean);
		} catch (SQLException e) {
			e.printStackTrace();
			showInfo(request, response, "亲,查询失败!");
			return null;
		}
    	return "/jsp/order_list.jsp";
    }
    /**
     * 根据id查找订单
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findOrderById(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	String oid = request.getParameter("oid");
    	try {
			Order order = service.findOrderById(oid);
			request.setAttribute("order", order);
		} catch (SQLException e) {
			e.printStackTrace();
			showInfo(request, response, "亲,查询失败!");
			return null;
		}
    	return "/jsp/order_info.jsp";
    }
    
    /**
     * 确认订单
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String confirmOrder(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	String oid = request.getParameter("oid");
    	String address = request.getParameter("address");
    	String name = request.getParameter("name");
    	String telephone = request.getParameter("telephone");
    	try {
			boolean updateOrder = service.updateOrder(address,name,telephone,oid);
			
			// 去调用第三方支付平台的接口,还需要传人家要的参数
			String p0_Cmd = "Buy";
			String p1_MerId = "10001126856";
			String p2_Order = oid;
			String p3_Amt = "0.01";//测试用1分钱，真正开发中用order.getTotal();
			String p4_Cur = "CNY";
			String p5_Pid = "";
			String p6_Pcat = "";
			String p7_Pdesc = "";
			String p8_Url = "http://localhost:8080"+request.getContextPath()+"/order?method=callBack";
			String p9_SAF = "0";
			String pa_MP = "";
			String pd_FrpId = request.getParameter("pd_FrpId");
			String pr_NeedResponse = "1";
			// 电子签名
			String hmac =PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");
			
			StringBuffer buffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
			buffer.append("p0_Cmd="+p0_Cmd);
			buffer.append("&p1_MerId="+p1_MerId);
			buffer.append("&p2_Order="+p2_Order);
			buffer.append("&p3_Amt="+p3_Amt);
			buffer.append("&p4_Cur="+p4_Cur);
			buffer.append("&p5_Pid="+p5_Pid);
			buffer.append("&p6_Pcat="+p6_Pcat);
			buffer.append("&p7_Pdesc="+p7_Pdesc);
			buffer.append("&p8_Url="+p8_Url);
			buffer.append("&p9_SAF="+p9_SAF);
			buffer.append("&pa_MP="+pa_MP);
			buffer.append("&pd_FrpId="+pd_FrpId);
			buffer.append("&pr_NeedResponse="+pr_NeedResponse);
			buffer.append("&hmac="+hmac);
			response.sendRedirect(buffer.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
			showInfo(request, response, "确认订单失败!");
			return null;
		}
    	return null;
    	
    }
    
    public String callBack(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
    	String oid = request.getParameter("r6_Order");
    	try {
			service.updateOrderState(1,oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	System.out.println("callBack");
    	request.setAttribute("msg", "订单号:"+oid+"支付成功");
    	return "jsp/info.jsp";
    }
}
