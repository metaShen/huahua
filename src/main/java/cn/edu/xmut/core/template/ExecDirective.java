package cn.edu.xmut.core.template;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * @描述 TODO Freemarker @exec标签
 * @用法 
 * 		标签@exec中有两个属性：src和name
 * 			src由spring框架设置的Controller名称，要调用的方法名，类似网址的键值对组成
 * 				Controller名称和方法名间，以"!"分开
 * 				方法名和键值对间，以"?"分开
 * 				键值对间，以"&"分开，若键值中含"&"，请使用"/&"替代
 * 				键和值以"="分开，若键值使用"="，请使用"/="替代
 * 			name为freeMarker页面的变量
 * 		@示例：<@exec src="controllerName!methodName?param=value&param2=value2" name='variable'/>
 * @特别说明
 * 		键值对（可多个键值对）：
 * 			键：方法中的变量名--值：为该变量名赋值
 * 			如果是实体，---键：实体中的变量名---值：为该变量名赋值
 * 			
 * @作者	陈财
 * @创建时间 2015年2月12日 下午11:05:48
 */
public class ExecDirective implements TemplateDirectiveModel {
	private String funcName,bean;
	private Map<String,String> paramsMap;
	private HttpServletRequest request;
	
	private static boolean isBaseType(String name){
		String baseTypes[]={"int","boolean","float","double","java.lang.String","java.lang.Object"};
        for (String type : baseTypes) {
			if(type.equals(name)){
				return true;
			}
		}
        return false;
	}
	private static Object toParse(String name,String value){
		String baseTypes[]={"int","boolean","float","double","java.lang.String","java.lang.Object"};
		Object object = null;
		if(name.equals(baseTypes[4])||name.equals(baseTypes[5])){
			object=value;
		}
		else if(StringUtils.isBlank(value)){
			value="0";
		}
		if(name.equals(baseTypes[0])){
			object=Integer.parseInt(value);
		}
		else if(name.equals(baseTypes[1])){
			object=Boolean.parseBoolean(value);
		}
		else if(name.equals(baseTypes[2])){
			object=Float.parseFloat(value);
		}
		else if(name.equals(baseTypes[3])){
			object=Double.parseDouble(value);
		}
		return object;
			
	}
	
	private void requestToMap(){
		@SuppressWarnings("rawtypes")
		Enumeration names = request.getParameterNames();
		while(names.hasMoreElements()){
			String key=names.nextElement().toString();
			String value=request.getParameter(key);
			paramsMap.put(key, value);
		}
	}
	
	private void analyze(String src){
		String array[],arr[];

		array=src.split("!",2);
		if(array.length!=2){
			throw new RuntimeException("@exec标签中src属性有误，是由ControllerName!MethodName组成");
		}
		bean=array[0];
		array=array[1].split("\\?",2);
		funcName=array[0];
		if(array.length==2){
			String prop = array[1];
			if(prop.length()>0&&prop.charAt(0)=='&')
				prop=prop.substring(1);
			prop=prop.replaceAll("([^/])&", "$1\n").replaceAll("/&", "&");
			prop=prop.replaceAll("([^/])=", "$1\b").replaceAll("/=", "=");
			array=prop.split("\n");
			for (String s : array) {
				if(s.matches(".+\b.*")){
					arr=s.split("\b");
					if(!arr[0].equals("")){
						paramsMap.put(arr[0], arr.length>1?arr[1]:"");
					}
				}
			}
		}
	}
	
	private Object exec(){
		WebApplicationContext applicationContext = RequestContextUtils.getWebApplicationContext(request);
		Object action = applicationContext.getBean(bean);
		if(action==null){
			throw new RuntimeException("@exec标签属性src有误，未找到Controller："+bean);
		}
		Class<?> cls = action.getClass();
		ClassPool pool=ClassPool.getDefault();
		try {
			//Constructor<?> constructor=cls.getDeclaredConstructor();
			//Object exec = constructor.newInstance();
			//pool.insertClassPath(new ClassClassPath(cls));

			/**
			 *  If a program is running on a web application server such as JBoss and Tomcat, 
			 *  the ClassPool object may not be able to find user classes 
			 *  since such a web application server uses multiple class loaders 
			 *  as well as the system class loader. 
			 *  In that case, an additional class path must be registered to the ClassPool. 
			 *	
			 *	所以加入下面这句，避免在部署到tomcat上出现javassist.NotFoundException的错误
			*/
			pool.insertClassPath(new ClassClassPath(cls));
			
			CtClass ctClass=pool.get(cls.getName());
			CtMethod ctMethod=null,ctMethods[]=ctClass.getDeclaredMethods();
			Method method = null,methods[]=cls.getDeclaredMethods();
			int i=0;
			for (CtMethod cm : ctMethods) {
				if(cm.getName().equals(funcName)){
					i++;
					ctMethod=cm;
				}
			}
			if(i==0){
				throw new RuntimeException("@exec标签属性src有误，该类内不存在"+funcName+"方法");
			}else if(i>1){
				throw new RuntimeException("@exec标签属性src有误，该类内只能有一个"+funcName+"方法，不支持使用多态");
			}
			for (Method m : methods) {
				if(m.getName().equals(funcName))
					method=m;;
			}
			MethodInfo methodInfo = ctMethod.getMethodInfo();
			CodeAttribute codeAttribute= methodInfo.getCodeAttribute();
			LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
	                .getAttribute(LocalVariableAttribute.tag);
			Class<?>[] paramTypes = method.getParameterTypes();
	        Object paramValues[] = new Object[paramTypes.length];
	        int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
	        for (i = 0; i < paramValues.length; i++) {
	        	if(isBaseType(paramTypes[i].getName())){
		        	String key=attr.variableName(i+pos);
		        	paramValues[i]=toParse(paramTypes[i].getName(),paramsMap.get(key));
		        	//paramsMap.remove(key);
	        	}
	        	else{
	        		Class<?> cl = paramTypes[i];
	        		if(cl==HttpServletRequest.class){
	        			paramValues[i]=request;
	        		}
	        		else{
		        		Constructor<?> con=cl.getDeclaredConstructor();
		        		Object ins = con.newInstance();
		        		Method[] ms = cl.getMethods();
		        		for (Method m : ms) {
		        			if(m.getParameterTypes().length==1&&isBaseType(m.getParameterTypes()[0].getName())&&m.getName().startsWith("set")){
				        		//System.out.println(m);
		        				String key=m.getName().substring(3);
			        			key=key.substring(0,1).toLowerCase()+key.substring(1);
								if(paramsMap.containsKey(key)){
									String value=paramsMap.get(key);
									Object o=toParse(m.getParameterTypes()[0].getName(), value);
									m.invoke(ins, o);
									//paramsMap.remove(key);
								}
		        			}
						}
		        		paramValues[i]=ins;
	        		}
	        	}
	        }
			return method.invoke(action,paramValues);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("@exec运行出错，完全关闭项目，再重新启动试试");
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		paramsMap=new HashMap<String,String>();
		
		Object srcObj = params.get("src");
		String src=srcObj==null?"":srcObj.toString();
		Object nameObj = params.get("name");
		String name=nameObj==null?"":nameObj.toString();
		if(StringUtils.isBlank(src)){
			throw new RuntimeException("@exec标签需要src属性");
		}
		if(StringUtils.isBlank(name)){
			throw new RuntimeException("@exec标签需要name属性");
		}
		requestToMap();
		analyze(src);
		Object object=exec();
		env.setVariable(name, ObjectWrapper.DEFAULT_WRAPPER.wrap(object));
		//env.setVariable("_"+name, ObjectWrapper.DEFAULT_WRAPPER.wrap(object));
	}
}