package cn.edu.xmut.core.template;

import org.springframework.stereotype.Component;

@Component("controllerUrl")
public class ControllerUrl {
	
	private String classPath;
	private String methodName;
	private String paramsQuery;
	private String uri;
	
	public static String ORIGIN_PATH = "cn.gov.mnhr.web.";
	
	public static ControllerUrl format(String uri) {
		ControllerUrl controllerUrl = new ControllerUrl();
		controllerUrl.uri = uri;
		if(uri.equals("") || uri == null || uri.indexOf(".") != 0) {
			return null;
		}
		String str[] = uri.substring(0,uri.indexOf("?")).split("!");
		if(str.length < 2) {
			return null;
		} else {
			String ctrlPath = str[0];
			controllerUrl.classPath = ORIGIN_PATH + ctrlPath.replace("/", ".");
			controllerUrl.methodName = str[1];
		}
		controllerUrl.setParamsQuery(null);
		
		return controllerUrl;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParamsQuery() {
		return paramsQuery;
	}

	public void setParamsQuery(String paramsQuery) {
		this.paramsQuery = paramsQuery;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
}
