/**
 * 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.edu.xmut.core.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.xmut.core.config.Global;
import cn.edu.xmut.core.utils.DateUtils;
import cn.edu.xmut.core.utils.StringUtils;

/**
 * 系统拦截器
 * @author lilin
 * @version 2013-01-15
 */
public class GlobalInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(GlobalInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	//*--------------待完成------------
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String uri = request.getRequestURI();
		String uriPrefix = request.getContextPath() + Global.ADMIN_PATH;
		// 拦截所有来自管理端的POST请求
		if ("POST".equals(request.getMethod()) && StringUtils.startsWith(uri, uriPrefix)) {
			//			User user = UserUtils.getUser();
			//			if (user != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("url: (" + request.getMethod() + ") " + uri);
			int index = 0;
			for (Object param : request.getParameterMap().keySet()) {
				sb.append((index++ == 0 ? "?" : "&") + param + "=");
				sb.append(StringUtils.abbr(request.getParameter((String) param), 100));
			}
			//				sb.append("; userId: " + user.getId());
			//				sb.append("; userName: " + user.getName());
			//				sb.append("; loginName: " + user.getLoginName());
			sb.append("; ipAddr: " + request.getLocalAddr());
			sb.append("; datetime: " + DateUtils.getDateTime());
			sb.append("; handler: " + handler.toString());
			logger.info(sb.toString());
			//			}
		}
	}

}
