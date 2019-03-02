/**
 * 
 */
package cn.edu.xmut.utils.bingyi.SpringFileUpload;

/**
 * @描述 TODO 常见文件Ext类型
 * @作者	陈财
 * @创建时间 2015年12月21日 下午4:36:39
 */
public class FileTypleByExtension {
	//bmp,gif,jpg,jpeg,jpe
	private static String imageExts[] = {".bmp",".gif",".jpeg",".png",".jpg"};
	//doc,docx,pdf
	private static String documentExts[] = {".doc",".docx",".pdf"};
	//video
	private static String videoExts[] = {".mp4",".mpeg",".mpg",".rm",".3gp",".rmve",".avi"};
	//ebook
	private static String eBookExts[] = {".mobi",".azw",".pdf",".azw3",".txt",".doc",".docx"};

	//获取扩展名并转为小写
	private static String toFileExt(String filename){
		String s[] = filename.split("\\.");
		if(s.length==1){
			return "";
		}else{
			return "."+s[s.length-1].toLowerCase();
		}
	}
	private static boolean inStringArray(String e,String arr[]){
		for (String a : arr) {
			if(e.equals(a)){
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean isImage(String filename){
		return inStringArray(toFileExt(filename), imageExts);
	}

	public static boolean isDocument(String filename){
		return inStringArray(toFileExt(filename), documentExts);
	}
	public static boolean isVideo(String filename){
		return inStringArray(toFileExt(filename), videoExts);
	}
	public static boolean isEBook(String filename){
		return inStringArray(toFileExt(filename), eBookExts);
	}
}
