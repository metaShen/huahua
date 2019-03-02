/**
 * 
 */
package cn.edu.xmut.utils.bingyi;

import java.util.Set;

import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSONObject;

/**
 * @描述 TODO 快捷简易工具
 * @作者	陈财
 * @创建时间 2015年3月5日 下午3:34:46
 */
public class EasyTools {
	/**
	 * 
	 * @描述 TODO 将json转为ModelMap格式（仅第一层转换）
	 * @作者         陈财
	 * @创建时间 2015年2月28日 上午12:01:14
	 */
	public static ModelMap jsonToModelMap(JSONObject json){
		Set<String> keyset = json.keySet();
		ModelMap modelMap = new ModelMap();
		for (String key : keyset) {
			Object o = json.get(key);
			if(o.getClass().getName().endsWith("List")){
				o = json.getJSONArray(key);
			}
			modelMap.put(key, o);
		}
		return modelMap;
	}
}
