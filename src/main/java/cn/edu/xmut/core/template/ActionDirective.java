package cn.edu.xmut.core.template;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class ActionDirective implements TemplateDirectiveModel {
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		SimpleScalar uriScalar = (SimpleScalar) params.get("uri");
		SimpleScalar nicknameScalar = (SimpleScalar) params.get("nickname");
		if(uriScalar==null){
			uriScalar = new SimpleScalar("");
		}
		if(nicknameScalar==null){
			nicknameScalar = new SimpleScalar("");
		}
		
		String uri =  StringUtils.trimToEmpty(uriScalar+"");
		String nickname = StringUtils.trimToEmpty(nicknameScalar+"");
		if(nickname.equals("")){
			throw new RuntimeException("@action指令需要nickname属性");
		}
		if(uri.equals("")){
			throw new RuntimeException("@action指令需要uri属性(uri=ControllerName+!+MethodName)");
		}
		String[] array = uri.split("!");
		
		if(array.length!=2){
			throw new RuntimeException("@action uri属性的值应由ControllerName+!+MethodName组成");
		}
		String ControllerName = array[0];
		String MethodName = array[1];
		WebApplicationContext applicationContext = RequestContextUtils.getWebApplicationContext(request);
		Object action = applicationContext.getBean(ControllerName);
		if(action==null){
			throw new RuntimeException("@action 未能找到Controller("+ControllerName+")");
		}
		try {
			Method method = action.getClass().getMethod(MethodName);
			Object ret = method.invoke(action);
			env.setVariable(nickname, ObjectWrapper.DEFAULT_WRAPPER.wrap(ret));
			env.setVariable("_"+nickname, ObjectWrapper.DEFAULT_WRAPPER.wrap(ret));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}