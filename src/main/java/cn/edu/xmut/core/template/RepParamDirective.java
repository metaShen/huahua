package cn.edu.xmut.core.template;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class RepParamDirective implements TemplateDirectiveModel{
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		StringModel dataModel=(StringModel) params.get("data");
		//SimpleScalar propertyScalar=(SimpleScalar) params.get("property");
		//SimpleScalar oriScalar=(SimpleScalar) params.get("ori");
		SimpleScalar replaceScalar=(SimpleScalar) params.get("replace");
		if(dataModel==null)
			throw new RuntimeException("@repparam指令需要data属性");
		if(replaceScalar==null)
			throw new RuntimeException("@repparam指令需要replace属性");
		Object obj=dataModel.getWrappedObject();
		String[] replace=StringUtils.trimToEmpty(replaceScalar+"").split("!");
		Field [] fs=obj.getClass().getDeclaredFields();
		
		for(Field f:fs)
		{
			f.setAccessible(true);
			try {
				String type=f.getType().toString();
				Object property=f.get(obj);
				
				if(type.equals("class java.lang.String")){
					if(property==null)
						f.set(obj,replace[0]);
					else if(property.equals(""))
						f.set(obj,replace[0]);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
//		else{
//			if(oriScalar==null)
//				throw new RuntimeException("@repparam指令需要ori属性");
//			String property=StringUtils.trimToEmpty(propertyScalar+"");
//			String[] ori=StringUtils.trimToEmpty(oriScalar+"").split("!");
//			if(ori.length!=replace.length)
//				throw new RuntimeException("@repparam指令 ori值和replace值数量不匹配");
//			boolean isfound=false;
//			for(Field f:fs)
//			{
//				String fieldName=f.getName();
//				if(fieldName.equalsIgnoreCase(property))
//				{
//					isfound=true;
//					f.setAccessible(true);
//					try {
//						Object comparevalue=f.get(obj);
//						for(int i=0;i<ori.length;i++)
//						{
//							if(ori[i].equals(comparevalue))
//							{}
//						}
//					} catch (IllegalArgumentException e) {
//						e.printStackTrace();
//					} catch (IllegalAccessException e) {
//						e.printStackTrace();
//					}
//					
//				}
//			}
//		}
	}

}
