/**
 * 
 */
package cn.edu.xmut.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.edu.xmut.core.utils.StringUtils;

/**
 * @author yangzj
 * @version 2014-8-18 下午3:47:26
 */
public class UtilCtrl {
	
	/**
	 * 获得request
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * 添加一个session
	 * @param name
	 * @param value
	 */
	public static void sessionPut(String name, Object value) {
		HttpSession session = getRequest().getSession();
		session.setAttribute(name, value);
	}
	
	public static void sessionPut(Object value) {
		String name = StringUtils.lowerCase(value.getClass().getSimpleName());
		HttpSession session = getRequest().getSession();
		session.setAttribute(name, value);
	}
	/**
	 * 
	* 把一个属性从session中移除
	* @param name void
	* @author chrimer(林江毅)
	* @date 2015年1月31日 下午9:39:54
	 */
	public static void sessionRemove(String name) {
		HttpSession session = getRequest().getSession();
		session.removeAttribute(name);
	}
	/**
	 * 返回当前登录的用户信息
	 * @param clazz
	 * @return
	 */
	public static Object currentUser(Class<?> clazz) {
		HttpSession session = getRequest().getSession();
		String name = StringUtils.lowerCase(clazz.getSimpleName());
		Object object = session.getAttribute(name);
		if(object != null) {
			return object;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T sessionGet(Class<?> clazz) {
		String name = StringUtils.lowerCase(clazz.getSimpleName());
		return sessionGet(name);
	}
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T sessionGet(String name) {
		HttpSession session = getRequest().getSession();
		return (T) session.getAttribute(name);
	}
	
	/**
	 * 返回当前用户登录的的远程IP地址
	 * @return
	 */
	public static String getIpAddr(){  
		String strIp = getRequest().getHeader("x-forwarded-for");
		if (strIp == null || strIp.length() == 0
				|| strIp.equalsIgnoreCase("unknown"))
			strIp = getRequest().getHeader("Proxy-Client-IP");
		if (strIp == null || strIp.length() == 0
				|| strIp.equalsIgnoreCase("unknown"))
			strIp =getRequest().getHeader("WL-Proxy-Client-IP");
		if (strIp == null || strIp.length() == 0
				|| strIp.equalsIgnoreCase("unknown"))
			strIp = getRequest().getRemoteAddr();
		strIp = strIp.split(",")[0].trim();
		return strIp;
	}  
	
	/**
	 * 获取 base path路径
	 * @return
	 */
	public static String getBasePath() {
		String path = getRequest().getContextPath();  
		String basePath = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+path+"/";
		return basePath;
	}
	
	/**
	 * 从请求中获得多字段参数。
	 * @param param
	 * @return
	 */
	public static String[] getRequestStringArray(String param) {
		String values[] = null;
		try {
			values = getRequest().getParameterValues(param);
		} catch(Exception e) {
			if(org.apache.commons.lang.StringUtils.isNotEmpty(getRequest().getParameter(param))) {
				values = new String[] {
					getRequest().getParameter(param)
				};
			} else {
				values = new String[0];
			}
		}
		return values;
	}
	
	/**
	 * 从请求中获得多字段参数。
	 * @param param
	 * @return
	 */
	public static Integer[] getRequestInegerArray(String param) {
		String values[] = null;
		try {
			values = getRequest().getParameterValues(param);
		} catch(Exception e) {
			if(org.apache.commons.lang.StringUtils.isNotEmpty(getRequest().getParameter(param))) {
				values = new String[] {
					getRequest().getParameter(param)
				};
			} else {
				values = new String[0];
			}
		}
		List<Integer> list = new ArrayList<Integer>();
		if(values != null) {
			for(String value : values) {
				if(org.apache.commons.lang.StringUtils.isNotEmpty(value)) {
					list.add(NumberUtils.toInt(value));
				} 
			}
		}
		Integer[] iArray = new Integer[list.size()];
		list.toArray(iArray);
		return iArray;
	}
	
	/**
	 * 从前台获取参数
	 * @param param
	 * @return
	 */
	public static String getRequestValueNotNull(String param) {
		return getRequestValueWithDefault(param, StringUtils.EMPTY);
	}
	
	/**
	 * 当获取的值为空时，返回默认值
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public static String getRequestValueWithDefault(String param, String defaultValue) {
		String value = getRequest().getParameter(param);
		if(StringUtils.isEmpty(value)) {
			return defaultValue;
		}
		return value;
	}
	
	/**
	 * 当获取的值为空时，默认出始值为0
	 * @param param
	 * @return
	 */
	public static int getRequestIntValueDefaultZero(String param) {
		return getRequestIntValueWithDefault(param, 0);
	}
	
	/**
	 * 当获取的值为空时，设定默认出始值
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public static int getRequestIntValueWithDefault(String param, int defaultValue) {
		String value = getRequest().getParameter(param);
		return NumberUtils.toInt(value, defaultValue);
	}
	/**
	 * 当获取的值为空时，默认出始值为0
	 * @param param
	 * @return
	 */
	public static float getRequestFloatValueDefaultZero(String param) {
		return getRequestFloatValueWithDefault(param, 0.0F);
	}
	
	/**
	 * 当获取的值为空时，设定默认出始值
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public static float getRequestFloatValueWithDefault(String param, float defaultValue) {
		String value = getRequest().getParameter(param);
		return NumberUtils.toFloat(value, defaultValue);
	}
	
	
	
//	public static Object getRequestValueDefaultZero(String param) {
//		return getRequestValueWithDefault(param, null);
//	}
//	/**
//	 * 当获取的值为空时，设定默认出始值
//	 * @param param
//	 * @param defaultValue
//	 * @return
//	 */
//	public static Object getRequestValueWithDefault(String param, Object defaultValue) {
//		String value = getRequest().getParameter(param);
//		String className = defaultValue.getClass().getName();
//		if(className.equals(Integer.class.getName())){
//			defaultValue=defaultValue==null?0:defaultValue;
//			return NumberUtils.toInt(value,(Integer) defaultValue);
//		}else if(className.equals(Float.class.getName())){
//			return NumberUtils.toFloat(value,(Float) defaultValue);
//		}else if(className.equals(Double.class.getName())){
//			return NumberUtils.toDouble(value,(Double) defaultValue);
//		}else if(className.equals(Long.class.getName())){
//			return NumberUtils.toLong(value,(Long) defaultValue);
//		}else{
//			return value==null||value.isEmpty()?defaultValue:value;
//		}
//	}
}
