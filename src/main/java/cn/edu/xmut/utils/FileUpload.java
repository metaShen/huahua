package cn.edu.xmut.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.xmut.core.utils.DateUtils;


public class FileUpload {
	
	private static final Logger log = LoggerFactory.getLogger(FileUpload.class);
	public static String DIR_UPLOAD = "/upload/images/";
	
	public static boolean Upload(MultipartFile[] files,String upload_dir) throws IOException{
		for(MultipartFile myfile : files){ 
            if(myfile.isEmpty()){ 
            	log.warn("文件未上传"); 
            }else{ 
            	log.info("文件长度: " + myfile.getSize()); 
            	log.info("文件类型: " + myfile.getContentType()); 
            	log.info("文件名称: " + myfile.getName()); 
            	log.info("文件原名: " + myfile.getOriginalFilename()); 

                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(upload_dir, myfile.getOriginalFilename())); 
            } 
        }
		return false;
	}
	
	public static String Upload(MultipartFile file,String upload_dir) throws IOException{
            if(file.isEmpty()){ 
                log.warn("文件未上传"); 
                return "文件未上传";
            }else{ 
                log.info("文件长度: " + file.getSize()); 
                log.info("文件类型: " + file.getContentType()); 
                log.info("文件名称: " + file.getName()); 
                log.info("文件原名: " + file.getOriginalFilename()); 
                Random random = new Random();
    			int math = random.nextInt(899999)+100000;
    			String fileName = file.getOriginalFilename();
    			int num = fileName.lastIndexOf(".");
                String fileSaveName = DateUtils.getNow()+ math+fileName.substring(num);
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(upload_dir,fileSaveName)); 
                return fileSaveName;
            } 
	}
	
	public static byte[] inputStreamToByte(InputStream is) throws IOException { 
        ByteArrayOutputStream bAOutputStream = new ByteArrayOutputStream(); 
        int ch; 
        while((ch = is.read() ) != -1){ 
            bAOutputStream.write(ch); 
        } 
        byte data [] =bAOutputStream.toByteArray(); 
        bAOutputStream.close(); 
        return data; 
    } 
}
