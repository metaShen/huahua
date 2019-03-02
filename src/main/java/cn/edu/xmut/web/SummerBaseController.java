package cn.edu.xmut.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class SummerBaseController implements Cloneable{
	public String getPath(HttpServletRequest request){
		return getPath(request,false);
	}
	public String getPath(HttpServletRequest request,boolean allowNextChild){
		String p = StringUtils.trimToNull(request.getParameter("p"));
		if(p==null) 
			return "index";
		if(!p.matches("[0-9a-zA-Z-_/]+"))
			return "index";
		if(!allowNextChild&&p.indexOf('/')>0)
			return "index";
		return p;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
