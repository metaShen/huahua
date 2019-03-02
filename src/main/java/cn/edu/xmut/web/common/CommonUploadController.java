package cn.edu.xmut.web.common;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.bingyi.SpringFileUpload.FileDownload;
import cn.edu.xmut.utils.bingyi.SpringFileUpload.FileUpload;


@Controller("commonUploadController")
@RequestMapping("common")

public class CommonUploadController extends BaseController{	
	
	@RequestMapping("upload/image")
	public @ResponseBody JSONObject uploadPic(MultipartFile file) throws IOException{
		FileUpload fileUpload = new FileUpload();
        fileUpload.upload(file,"images",FileUpload.FILE_TYPE_IMAGE);
        if(fileUpload.isSuccess()){
        	JSONObject json = new JSONObject();
        	json.put("error", false);
        	json.put("name", fileUpload.getFilename());
        	json.put("ext", fileUpload.getFileExt());
        	return json;
        }else{
        	return JsonTool.genErrorMsg(fileUpload.getMsg());
        }
	}
	
	@RequestMapping("upload/ebook")
	public @ResponseBody JSONObject uploadEBook(MultipartFile file) throws IOException{
		FileUpload fileUpload = new FileUpload();
        fileUpload.upload(file,"ebooks",FileUpload.FILE_TYPE_EBOOK);
        if(fileUpload.isSuccess()){
        	JSONObject json = new JSONObject();
        	json.put("error", false);
        	json.put("name", fileUpload.getFilename());
        	json.put("ext", fileUpload.getFileExt());
        	return json;
        }else{
        	return JsonTool.genErrorMsg(fileUpload.getMsg());
        }
	}
	
	@RequestMapping("download/image")
	public @ResponseBody ResponseEntity<byte[]> downloadPic(String filename) throws IOException {
		FileUpload fileUpload = new FileUpload();
		String path = fileUpload.convertIntoRootPath(fileUpload.getDefaultUploadDir()+"/images/"+filename);
		return FileDownload.getResponseEntity(path, MediaType.IMAGE_JPEG);
	}
	@RequestMapping("download/ebook")
	public @ResponseBody ResponseEntity<byte[]> downloadEBook(String filename) throws IOException {
		FileUpload fileUpload = new FileUpload();
		String path = fileUpload.convertIntoRootPath(fileUpload.getDefaultUploadDir()+"/ebooks/"+filename);
		return FileDownload.getResponseEntity(path, MediaType.APPLICATION_OCTET_STREAM);
	}
	
	/**
	@RequestMapping("download/image/{filename}.{ext}")
	public @ResponseBody ResponseEntity<byte[]> downloadPic(@PathVariable("filename") String filename,@PathVariable("ext") String ext) throws IOException {
		FileUpload fileUpload = new FileUpload();
		String path = fileUpload.convertIntoRootPath(fileUpload.getDefaultUploadDir()+"/images/"+filename+"."+ext);
		return FileDownload.getResponseEntity(path, MediaType.IMAGE_JPEG);
	}
	
	@RequestMapping("download/ebook/{filename}.{ext}")
	public @ResponseBody ResponseEntity<byte[]> downloadEBook(@PathVariable("filename") String filename,@PathVariable("ext") String ext) throws IOException {
		FileUpload fileUpload = new FileUpload();
		String path = fileUpload.convertIntoRootPath(fileUpload.getDefaultUploadDir()+"/ebooks/"+filename+"."+ext);
		return FileDownload.getResponseEntity(path, MediaType.IMAGE_JPEG);
	}
	**/
	
	
	@RequestMapping("upload/video")
	public @ResponseBody JSONObject uploadVideo(MultipartFile file) throws IOException{
		FileUpload fileUpload = new FileUpload();
        fileUpload.upload(file,"video",FileUpload.FILE_TYPE_VIDEO);
        if(fileUpload.isSuccess()){
        	JSONObject json = JsonTool.genSuccessMsg(fileUpload.getFilenameAndExt());
        	return json;
        }else{
        	JSONObject json = JsonTool.genErrorMsg(fileUpload.getMsg());
        	return json;
        }
	}
	@RequestMapping("download/video")  
	public void downloadVideo(String p,HttpServletResponse response) throws IOException { 
		FileUpload fileUpload = new FileUpload(); 
		String path = fileUpload.convertIntoRootPath(fileUpload.getDefaultUploadDir()+"/video/"+p);
		FileDownload.setResponse(response, path,MediaType.APPLICATION_OCTET_STREAM_VALUE);
	}
	/**
	 * 
	 * @描述 TODO 上传文件大小限制的异常拦截
	 */
	@ExceptionHandler(Exception.class)
    public @ResponseBody JSONObject handleException(Exception ex){
		String data="未知错误！";
		if(ex instanceof MaxUploadSizeExceededException){
			data = "文件大小限制为100M！";
		}else {
			System.out.println(ex);
		}
		return JsonTool.genErrorMsg(data);
	}
	
}
