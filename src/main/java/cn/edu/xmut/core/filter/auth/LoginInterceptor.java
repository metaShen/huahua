package cn.edu.xmut.core.filter.auth;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//import cn.edu.xmut.modules.admin.user.bean.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	private static final Logger LOG = LoggerFactory
			.getLogger(LoginInterceptor.class);
			
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		LoginRequired annotation = method.getAnnotation(LoginRequired.class);
		if(null == annotation) {
			return true;
		}
		HttpSession session = request.getSession();
		String url = request.getRequestURI();
		if(url.endsWith("xmut/") || url.endsWith("login.jhtml") || url.endsWith("xmut")) {
			return true;
		}
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//String user  = null;
		//User user  = (User) session.getAttribute("user");
//		if(null == user) {
//			response.sendRedirect(basePath+"admin/login.jhtml");
//			LOG.warn("After logging in, to do the next operation,url:"+request.getServletPath());
//			return false;
//		}
		return true;
	}
}
