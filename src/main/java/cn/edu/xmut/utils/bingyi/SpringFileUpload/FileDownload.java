/**
 * 
 */
package cn.edu.xmut.utils.bingyi.SpringFileUpload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.omg.CORBA_2_3.portable.OutputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * @author cc
 *
 */
public class FileDownload {
	public static void setResponse(HttpServletResponse response,String path)  throws IOException {
		setResponse(response, path,"multipart/form-data");
	}
	public static void setResponse(HttpServletResponse response,String path,String contentType)  throws IOException {
		//response.reset();
		response.setCharacterEncoding("utf-8");
        response.setContentType(contentType);
        String filename = path.substring(path.lastIndexOf("/")+1);
        response.setHeader("Content-Disposition", "attachment;fileName="+filename);
		File file = new File(path);
        response.setHeader("Content-Length", ""+file.length());
	    ServletOutputStream os = response.getOutputStream();
//	    InputStream inputStream = new FileInputStream(file);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) > 0) {
//            os.write(b, 0, length);
//        }
        //os.flush();
        os.write(FileUtils.readFileToByteArray(file));
        os.close();
        //inputStream.close();
	}
	public static ResponseEntity<byte[]> getResponseEntity(String path,MediaType mediaType) throws IOException{
        String filename = path.substring(path.lastIndexOf("/")+1);
	    File file = new File(path);
	    if(!file.exists()) {
	    	//不存在的图片进行替换
	    	file = new File(path.replace(filename, "defalut.jpg"));
	    }
		HttpHeaders headers = new HttpHeaders();
		if(filename.endsWith(".jpg")||filename.endsWith(".jpeg")) {
			headers.set("Content-Type", "image/jpeg");
		}else if (filename.endsWith(".png")) {
			headers.set("Content-Type", "image/png");
		}else if (filename.endsWith(".gif")) {
			headers.set("Content-Type", "image/gif");
		}else if (filename.endsWith(".bmp")) {
			headers.set("Content-Type", "image/bmp");
		}else if (filename.endsWith(".mp4")) {
			headers.set("Content-Type", "video/mp4");
		}else {
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
		}
		headers.setExpires(System.currentTimeMillis()+Long.parseLong("2592000000"));
		headers.setCacheControl("max-age=2592000");
	    headers.setLastModified(file.lastModified());
		headers.setContentLength(file.length());
	    //headers.setContentDispositionFormData("attachment", filename);  
	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),  
	                                      headers, HttpStatus.OK);  
	}
}
