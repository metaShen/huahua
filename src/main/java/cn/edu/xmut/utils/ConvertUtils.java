package cn.edu.xmut.utils;
/**
 * 
 * @描述 转换
 * @作者 bob(傅文城)
 * @创建时间 2015年2月2日 下午10:02:31
 */
public class ConvertUtils {
	
	/**
	 * 
	 * @描述 对页面接收的特殊字符进行转码
	 * @请求参数 String src
	 * @作者  bob(傅文城)
	 * @创建时间 2015年2月2日 下午10:03:55
	 */
	public static String toHtml(String src){
		String target="";
		target = src.replaceAll("&lt;","<").replaceAll("&gt;", ">").replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
		return target;
	}
	
	
	/**
	 * 
	 * @描述 过滤html标签
	 * @请求参数 String src
	 * @作者  bob(傅文城)
	 * @创建时间 2015年2月2日 下午10:05:11
	 */
	public static String totext(String src){
		String target="";
		target = src.replaceAll("</?[^>]+>", "");
		return target;
	}
}
