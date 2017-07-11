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
import com.zouensi.utils.UUIDUtils;

/**
 * 
 * @author zouensi
 * @date 2017��7��7��
 * ����:����servlet
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
     * ��Ӷ���
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
    		request.setAttribute("errorMsg", "����е�¼");
    		return "/jsp/cart.jsp";
    	}else {
    		//��װorder
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
    		//�ӹ��ﳵ�л�ȡ������Ͻ��б���
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
    			//������ӳɹ��Ƴ�session�е���Ϣ
    			session.removeAttribute("cart");
    		}else {
    			showInfo(request, response, "��,���ɶ���ʧ��");
    			return null;
    		}
    		return "/jsp/order_info.jsp";
    	}
    }
    
    public String findOrders(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	//��ȡ�û���Ϣ
    	HttpSession session = request.getSession();
    	User user = (User)session.getAttribute("user");
    	if(user==null) {
    		showInfo(request, response, "��,���ȵ�¼�ڽ��ж�����ѯ����!");
			return null;
    	}
    	int pageNumber = 4;
    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    	try {
			PageBean pageBean = service.findOrders(user,pageNumber,pageSize);
			System.out.println(pageBean.toString());
			request.setAttribute("pageBean", pageBean);
		} catch (SQLException e) {
			e.printStackTrace();
			showInfo(request, response, "��,��ѯʧ��!");
			return null;
		}
    	return "/jsp/order_list.jsp";
    }
}
