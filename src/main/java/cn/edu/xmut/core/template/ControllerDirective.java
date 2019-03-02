package cn.edu.xmut.core.template;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import static cn.edu.xmut.core.template.BlockDirectiveUtils.*;

@Component("controllerDirective")
public class ControllerDirective implements TemplateDirectiveModel {

	public static Logger LOG = LoggerFactory.getLogger(ControllerDirective.class);
	public static final String BLOCK_NAME = "uri";
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		String blockName = getBlockName(env, params, BLOCK_NAME);
		ControllerUrl controllerUrl = ControllerUrl.format(blockName);
		Object o = null ;
		try {
			Class<?> c = Class.forName(controllerUrl.getClassPath());
			Method method = c.getMethod(controllerUrl.getMethodName());
			o = method.invoke(c.newInstance());
			System.out.println("classname：：：："+c);
			System.out.println("ooooooooooooo：：：："+o);
		} catch (ClassNotFoundException e) {
			LOG.error("you request uri is not exist,pleace check you uri value again");
		} catch (NoSuchMethodException e) {
			LOG.error("you request uri is not exist,pleace check you uri value again");
		} catch (SecurityException e) {
			LOG.error("you request uri is not exist,pleace check you uri value again");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		env.setVariable("blist", ObjectWrapper.DEFAULT_WRAPPER.wrap(o));
	}

}
