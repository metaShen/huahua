package cn.edu.xmut.core.template;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.SimpleHash;
import freemarker.template.SimpleScalar;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class ToJsonDirective implements TemplateDirectiveModel {
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Object data = params.get("data");
		if(data==null){
			throw new RuntimeException("@toJson指令需要data属性");
		}
		SimpleHash ex = (SimpleHash) params.get("ex");
		JsonConfig config = new JsonConfig(); 
		if(ex!=null){
			config.setIgnoreDefaultExcludes(true);
			config.setExcludes(ex.toString().split(";"));
		}
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		 String json = null;
		// System.out.println(data.getClass());
		if(SimpleHash.class.equals(data.getClass())){
			json = JSONObject.fromObject(((SimpleHash)data).toMap(),config).toString();
		}else if(StringModel.class.equals(data.getClass())){
			json = JSONObject.fromObject(data,config).toString();
		}else if(SimpleSequence.class.equals(data.getClass())){
			json = JSONArray.fromObject(((SimpleSequence)data).toList(),config).toString();
		}
        SimpleScalar  encode = (SimpleScalar ) params.get("encode");
        boolean encodeBl = false;
        if(encode==null||encode.toString().equalsIgnoreCase("true")){
        	encodeBl = true;
        }
//        for(Object name:env.getKnownVariableNames()){
//        	System.out.println(name);
//        }
        if(json==null){
        	return;
        }
        if(encodeBl){
        	env.getOut().write(URLEncoder.encode(json,"UTF-8"));
        }else{
        	env.getOut().write(json);
        }
		
	}
}