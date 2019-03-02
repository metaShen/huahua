/**
 * 
 */
package cn.edu.xmut.utils.bingyi;

/**
 * @描述 TODO 获取真实路径
 * @作者	陈财
 * @创建时间 2016年1月26日 下午7:34:37
 * @版本 v1.0.1
 */
public class RealPathUtil {
	//将相对于webinf(WebContent)的路径转为真实路径（文件的完整路径）
	public static String convertIntoWebInf(String path){
		if(path.startsWith("/")){
			path = path.substring(1);
		}
		String p = convertIntoClass("");
		if(p!=null){
			int i = p.lastIndexOf("classes");
			if(i!=-1){
				p = p.substring(0,i)+path;
			}else{
				p=null;
			}
		}
		return p;
	}
	//将相对于webapp(WebContent)的路径转为真实路径（文件的完整路径）
	public static String convertIntoWebapp(String path){
		if(path.startsWith("/")){
			path = path.substring(1);
		}
		String p = convertIntoClass("");
		if(p!=null){
			int i = p.lastIndexOf("WEB-INF/classes");
			if(i!=-1){
				p = p.substring(0,i)+path;
			}else{
				p=null;
			}
		}
		return p;
	}
	/**
	 * @描述 TODO 这里根目录：Windows指的是服务器运行所在的盘，Linux指的是系统根目录（bea）
	 */
	public static String convertIntoRootPath(String path){
		if(path.startsWith("/")){
			path = path.substring(1);
		}
		String p = convertIntoClass("");
		if(p!=null){
			p=p.replaceAll("^(/?[\\w:]+/).*", "$1")+path;
		}
		return p;
	}
	//将相对于src的路径转为真实路径（文件的完整路径）
	public static String convertIntoClass(String path){
		if(path.startsWith("/")){
			path = path.substring(1);
		}
		String p;
		//直接执行的话要使用getClass
		try {
			p = RealPathUtil.class.getClassLoader().getResource("/").getPath();
		} catch (Exception e) {
			p = RealPathUtil.class.getClass().getResource("/").getPath();
		}
		if(p!=null){
			p=p.replaceAll("\\\\", "/");
			p = p + path;
			p = p.replaceAll("^/(\\w:/)", "$1");//Windows上获取的第一个字符为"/",所以去除
		}
		return p;
	}
	public static void main(String[] args) {
		System.out.println(convertIntoClass(""));
		System.out.println(convertIntoWebapp(""));
		System.out.println(convertIntoWebInf(""));
		System.out.println(convertIntoRootPath(""));
	}
}
