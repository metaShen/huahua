package cn.edu.xmut.utils;

import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;





/**
 * @author yangzj
 * @version 2014-8-7 下午2:54:49
 */
public class JsonTool {
	/**
	 * 
	 * @描述  返回bean验证失败信息
	 * @参数
	 * List list bean验证失败信息list
	 * @作者  chrimer(林江毅)
	 * @创建时间 2015年4月5日 下午3:32:56
	 */
	public static JSONObject genBeanValidateErrorMsg(List<String> list) {
		String messages = "验证失败";
		if(list!=null && list.size()>0){
			messages = "验证失败，原因：<br/>";
			for(int i=0;i<list.size();i++){
				messages += "<"+(i+1)+">"+ list.get(i)+"<br/>";
			}
		}
		JSONObject json = new JSONObject();
		json.put("error", true);
		json.put("data", messages);
		return json;
	}
	/**
	 * 除保留对应的数据之外，其他都清理掉。<br>
	 * 
	 * @param oldjobj
	 * @param leaveKeys
	 * @return
	 */
	public static JSONObject cleanRowLeaveKeys(JSONObject oldjobj,
			String... leaveKeys) {
		if (oldjobj == null || oldjobj.isEmpty())
			return oldjobj;
		JSONObject jobj = oldjobj;
		Set<String> keysOfjObj = jobj.keySet();
		for (String key : leaveKeys) {
			if (!keysOfjObj.contains(key)) { // 不在保留名单里头的，清除。
				jobj.remove(key);
			}
		}
		return jobj;
	}
	
	/**
	 * 返回异常信息
	 * 
	 * @param msg
	 * @return
	 */
	public static JSONObject genErrorMsg(Object data) {
		JSONObject json = new JSONObject();
		json.put("error", true);
		json.put("data", data);
		return json;
	}
	
	/**
	 * 返回异常信息 
	 * 
	 * @param name
	 * @param data
	 * @return
	 */
	public static JSONObject genErrorMsg(String name, Object data) {
		JSONObject json = new JSONObject();
		json.put("error", true);
		json.put("name", name);
		json.put("data", data);
		return json;
	}
	
	/**
	 * 返回普通信息
	 * 
	 * @param msg
	 * @return
	 */
	public static JSONObject genSuccessMsg(Object data) {
		JSONObject json = new JSONObject();
		json.put("error", false);
		json.put("data", data);
		return json;
	}
	
	/**
	 * 返回普通信息
	 * 
	 * @param name
	 * @param data
	 * @return
	 */
	public static JSONObject genSuccessMsg(String name, Object data) {
		JSONObject json = new JSONObject();
		json.put("error", false);
		json.put("name", name);
		json.put("data", data);
		return json;
	}
	
	/**
	 * 创建操作成功后返回的信息
	 * 
	 * @param data
	 * @param idKeys
	 * @param addId
	 * @return
	 */
	public static JSONObject genAddSuccessMsg(Object data, String[] idKeys, Object...addId) {
		JSONObject json = new JSONObject();
		json.put("error", false);
		json.put("data", data);
		int loop = 0;
		for(String id : idKeys) {
			if(addId.length >= loop) {
				json.put(id, addId[loop]);
			}
		}
		return json;
	}
	
	/**
	 * 创建操作成功后返回的信息
	 * 
	 * @param data
	 * @param idKeys
	 * @param addId
	 * @return
	 */
	public static JSONObject genAddSuccessMsg(Object data, Object addId) {
		return genAddSuccessMsg(data, new String[]{"id"}, addId);
	}
	
	/**
	 * fastjson null值转换为 “”
	 * @param object
	 * @param features
	 * @return
	 */
	public static String toJSONString(Object object, 
			SerializerFeature ...features) {
		SerializeWriter out = new SerializeWriter();
		String s;
		JSONSerializer serializer = new JSONSerializer(out);
		SerializerFeature arr$[] = features;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			SerializerFeature feature = arr$[i$];
			serializer.config(feature, true);
		}

		serializer.getValueFilters().add(new ValueFilter() {
			public Object process(Object obj, String s, Object value) {
				if(null!=value) {
					if(value instanceof java.util.Date) {
						return String.format("%1$tF %1tT", value);
					}
					return value;
				}else {
					return "";
				}
			}
		});
		serializer.write(object);
		s = out.toString();
		out.close();
		return s;
	}
}
