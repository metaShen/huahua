/**
 * 
 */
package cn.edu.xmut.utils.bingyi.SpringFileUpload;

/**
 * @描述 TODO 常见文件MIME类型
 * @作者	陈财
 * @创建时间 2015年12月21日 下午4:36:39
 */
public class FileMIME {
	//bmp,gif,jpg,jpeg,jpe
	private static String imageMIMEs[] = {"image/bmp","image/gif","image/jpeg","image/png"};
	//doc,docx,pdf
	private static String documentMIMEs[] = {"application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document","application/pdf","application/x-download"};

	private static boolean inStringArray(String e,String arr[]){
		for (String a : arr) {
			if(e.equals(a)){
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean isImage(String mime){
		return inStringArray(mime, imageMIMEs);
	}
	
	public static boolean isDocument(String mime){
		return inStringArray(mime, documentMIMEs);
	}
}
