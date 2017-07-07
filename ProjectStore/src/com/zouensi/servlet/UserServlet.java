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
 * @date 2017��7��7��
 * ����:�û�servlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();
    public UserServlet() {
        super();
    }
    /**
     * ע��ҳ����ת
     * @return
     */
    public String register(HttpServletRequest request,HttpServletResponse response) {
    	
		return "/WEB-INF/jsp/register.jsp";
    }
    
    /**
     * ��¼ҳ����ת
     * @return
     */
    public String login(HttpServletRequest request,HttpServletResponse response) {
    	
		return "/WEB-INF/jsp/login.jsp";
    }
    /**
     *�˳���¼ 
     */
    public String quit(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	HttpSession session = request.getSession();
    	session.setAttribute("user", null);
    	session.invalidate();
    	//�����Զ���¼��ϵ�������������cookie����cookieҲҪ�Ƴ�
    	Cookie[] cookies = request.getCookies();
    	for (Cookie cookie : cookies) {
    		if("username".equals(cookie.getName())) {
    			cookie.setMaxAge(0);//age����Ϊ0����ʧЧ
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
     * ע����Ϣ
     * @return
     * @throws IOException 
     * @throws ServletException 
     */
    public String registerInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	Map<String, String[]> parameterMap = request.getParameterMap();
    	//��ȡ�û��������֤��
    	String userYum = request.getParameter("userYum");
    	//��ȡsession�е���֤��
    	HttpSession session = request.getSession();
    	String sessionYum = (String) session.getAttribute("yum");
    	if(userYum==null||sessionYum==null||sessionYum.equalsIgnoreCase(userYum)) {
    		request.setAttribute("msg","��֤���������");
    		session.setAttribute("yum", "");//���û�������������֤��
    		return "/WEB-INF/jsp/register.jsp?method=registerInfo";
    	}
    	User user = new User();
    	try {
			BeanUtils.populate(user, parameterMap);
			//��ȡ����
			String uid = UUIDUtils.getUUID();
			user.setUid(uid);
			//���ü���״̬,��û�м�������Ϊ0
			user.setState(0);
			//��ȡ����code
			String code =  UUIDUtils.getUUID()+ UUIDUtils.getUUID();
			user.setCode(code);
			boolean state = service.registerInfo(user);
			if(state) {
				request.setAttribute("msg", "��,ע��ɹ�,������ʼ����м���");
				// �첽���ʼ�
				String emailMsg="��,�����ʼ�<a href=http://localhost:8081/ProjectStore/UserServlet?method=activate&code="
						+user.getCode()+">�������</a>";
				Runnable runnable = new EmailRunnable(user.getEmail(), emailMsg);
				ThreadPoolExecutor pool = EmaliThreadPool.getPool();
				pool.execute(runnable);
			}
		} catch (Exception e) {
			showInfo(request, response, "��,ע��ʧ��,������ע��!");
			e.printStackTrace();
		}
    	session.setAttribute("yum", "");
    	//�ɹ���ת��infoҳ����ʾ
		return "/WEB-INF/jsp/info.jsp";
    }
    
    public String activate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	//��ȡ����code
    	String code = request.getParameter("code");
    	try {
			User user = service.getUser(code);
			if(user==null) {
				showInfo(request, response, "��,�����ʼ�����,�����¼���!");
				return null;
			}else {
				user.setState(1);
				boolean state = service.updateUser(user);
				if(state) {
					request.setAttribute("msg", "��,����ɹ�,����е�¼");
					return "/WEB-INF/jsp/login.jsp";
				}else {
					request.setAttribute("msg", "��,����ʧ��!");
				}
			}
		} catch (SQLException e) {
			showInfo(request, response, "��,����ʧ��!");
			e.printStackTrace();
		}
    	return "/WEB-INF/jsp/info.jsp";
    }
    /**
     * �û���¼
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String loginInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	String url = "";
    	//��ȡ�û��������֤��
    	String userYum = request.getParameter("userYum");
    	//��ȡsession�е���֤��
    	HttpSession session = request.getSession();
    	String sessionYum = (String) session.getAttribute("yzm");
    	session.setAttribute("yzm", "");//���û�������������֤��
    	if(userYum==null||sessionYum==null||!sessionYum.equalsIgnoreCase(userYum)) {
    		request.setAttribute("errorMsg","��֤���������");
    		return "/WEB-INF/jsp/login.jsp?method=loginInfo";
    	}
    	String zdLogin = request.getParameter("zdLogin");
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	try {
			User user = service.loginInfo(username,password);
			if(user!=null) {
				if(user.getState()==null||user.getState()==0) {
					//��û�м���
					request.setAttribute("errorMsg", "��,���м���,���ȼ����˺�!");
					url = "/WEB-INF/jsp/login.jsp?method=loginInfo";
				}else {
					if("1".equals(zdLogin)) {
						Cookie cookieUsername = new Cookie("username",user.getUsername());
						cookieUsername.setMaxAge(60*60*24*7);
						Cookie cookiePassword = new Cookie("password",user.getPassword());
						cookiePassword.setMaxAge(60*60*24*7);
						//���cookie
						response.addCookie(cookieUsername);
						response.addCookie(cookiePassword);
					}
					//��¼�ɹ�
					session.setAttribute("user", user);
					response.sendRedirect(request.getContextPath());
					//����null����������ת��
					return null;
				}
				
			}else {
				//�û�������
				request.setAttribute("errorMsg", "��,�û����������벻��ȷ!");
				url = "/WEB-INF/jsp/login.jsp?method=loginInfo";
			}
		} catch (SQLException e) {
			showInfo(request, response, "��,��¼ʧ��,�����µ�¼");
			e.printStackTrace();
		}
    	return url;
    }
}
