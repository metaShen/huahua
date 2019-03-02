package cn.edu.xmut.core.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

public class BasePathView extends FreeMarkerView {

	public static final String CONTEXT_PATH = "base";

	@Override
	protected void exposeHelpers(Map model, HttpServletRequest request)
			throws Exception {
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		model.put(CONTEXT_PATH, basePath);
		super.exposeHelpers(model, request);
	}

}
