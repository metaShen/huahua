package cn.edu.xmut.core.filter.permission;


import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//import cn.edu.xmut.modules.admin.user.bean.Permission;
//import cn.edu.xmut.modules.admin.user.bean.User;



public class PermissionInterceptor extends HandlerInterceptorAdapter{
	private static final Logger LOG = LoggerFactory
			.getLogger(PermissionInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean flag = false;

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		
	    // 获取注解上的类
	    Class<?> clazz = method.getDeclaringClass();
		Filter classFilter = clazz.getAnnotation(Filter.class);
		// 获取注解上的方法
		Filter methodFilter = method.getAnnotation(Filter.class);
		// 判断是否有拦截
		if(null == classFilter && null == methodFilter) {
			return true;
		}
		
		HttpSession session = request.getSession();
		
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		return true;
		
		/*User user  = (User) session.getAttribute("user");
		@SuppressWarnings("unchecked")
		List<Permission> permissions = (List<Permission>) session.getAttribute("lstPermission");
		String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + request.getRequestURI();
		String query = request.getQueryString();
		String from =URLEncoder.encode(url+(query == null ? "" : "?" + query),"UTF-8");
		//String from =url+(query == null ? "" : "?" + query);
		if(url.endsWith("ny/") || url.endsWith("login.jhtml") || url.endsWith("ny")) {
			return true;
		}
		
		if(null == user) {
			response.sendRedirect(basePath+"admin/login.jhtml?from="+from);
			LOG.warn("After logging in, to do the next operation,url:"+request.getServletPath());
			return false;
		}
		flag = isIntercept(request, response, classFilter, methodFilter, permissions);
		if(flag) {
			return flag;
		} else {
			LOG.warn("After logging in using different user with permission, to do the next operation,url:"+request.getServletPath());
			response.sendRedirect(basePath+"admin/login.jhtml?from="+from);
			return flag;
		}*/
	}

	/*private boolean isIntercept(HttpServletRequest request, HttpServletResponse response, Filter classFilter, Filter methodFilter, List<Permission> permissions) throws Exception {
		boolean flag = false;
		
		PermissionTypeEnum[] classPermissions = null;
		PermissionTypeEnum[] methodPermissions = null;
		
		if(null != classFilter) {
			classPermissions =classFilter.permission();
			for(PermissionTypeEnum p : classPermissions) {
				if(permissions.indexOf(p.toString())>=0){
					flag = true;
				}
			}
		} 
		
		if(null != methodFilter) {
			methodPermissions = methodFilter.permission();
			if(classPermissions != methodPermissions) {
				flag = false;
				for(PermissionTypeEnum p : methodPermissions) {
					if(permissions.indexOf(p.toString())>=0){
						flag = true;
					}
				}
			}
		}
		return flag;
	}*/

}
