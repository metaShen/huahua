/**
 * 
 */
package cn.edu.xmut.utils.bingyi.SpringFileUpload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

/**
 * @描述 TODO Spring文件上传
 * @作者 陈财
 * @创建时间 2015年12月21日 下午4:35:18
 */
public class FileUpload {
	public final static int FILE_TYPE_IMAGE = 0xf1;
	public final static int FILE_TYPE_DOCUMENT = 0xf2;
	public final static int FILE_TYPE_VIDEO = 0xf3;
	public final static int FILE_TYPE_EBOOK = 0xf4;

	private String errorFlag = "?";
	private String successFlag = ":";
	// 默认上传基本路径
	private String defaultUploadDir = "upload";
	// 上传完整路径
	private String uploadDir = "";
	// 上传完后的消息
	private String msg = "";
	// 文件名
	private String filename = "";
	// 扩展名（包含点）
	private String fileExt = "";

	public FileUpload() {
	}

	public FileUpload(String defaultUploadDir) {
		this.defaultUploadDir = defaultUploadDir;
	}

	public String getDefaultUploadDir() {
		return defaultUploadDir;
	}

	public void setDefaultUploadDir(String defaultUploadDir) {
		this.defaultUploadDir = defaultUploadDir;
	}

	public boolean isSuccess() {
		return msg.startsWith(successFlag);
	}

	public String getMsg() {
		return msg.substring(1);
	}

	// 获取扩展名并转为小写
	private String toFileExt(String name) {
		String s[] = name.split("\\.");
		if (s.length == 1) {
			return "";
		} else {
			return "." + s[s.length - 1].toLowerCase();
		}
	}

	private boolean isFileType(String filename, int FILE_TYPE) {
		if (FILE_TYPE == FILE_TYPE_IMAGE) {
			return FileTypleByExtension.isImage(filename);
		} else if (FILE_TYPE == FILE_TYPE_DOCUMENT) {
			return FileTypleByExtension.isDocument(filename);
		} else if (FILE_TYPE == FILE_TYPE_VIDEO) {
			return FileTypleByExtension.isVideo(filename);
		} else if (FILE_TYPE == FILE_TYPE_EBOOK) {
			return FileTypleByExtension.isEBook(filename);
		}else {
			return false;
		}
	}

	public void upload(MultipartFile file, String filename, String nextUploadDir, int FILE_TYPE, Boolean isCompress) {
		if (file == null || file.isEmpty()) {
			msg = errorFlag + "文件未上传！";
		} else if (!isFileType(file.getOriginalFilename(), FILE_TYPE)) {
			msg = errorFlag + "文件上传类型有误！";
		} else {
			String oriName = file.getOriginalFilename();
			this.filename = filename;
			fileExt = toFileExt(oriName);
			String ud = convertIntoRootPath(defaultUploadDir + "/" + nextUploadDir);			
			try {
				new File(ud).mkdirs();
				//因为这是统一的上传接口，所以判断一下如果是图片才 进行压缩
				if (FILE_TYPE == FILE_TYPE_IMAGE) {
					//这里就是用Thumbnails进行压缩：
					//scale(1f)不缩放   outputQuality压缩质量0.7 toFile写到文件中
					if (!fileExt.equals(".gif") || true == isCompress) {
					BufferedImage Imgs = ImageIO.read(file.getInputStream());
					if(Imgs.getWidth()>1600 || Imgs.getHeight()>1600){
						Thumbnails.of(file.getInputStream()).size(1600,1200).outputQuality(0.7).toFile(new File(ud, filename + fileExt));
					}
					else {
						Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.7).toFile(new File(ud, filename + fileExt));
					}
					
				}else {
					copyInputStreamToFile(file.getInputStream(), new File(ud, filename + fileExt));
				}
					Thumbnails.of(file.getInputStream()).forceSize(240, 240).outputQuality(0.5)
					.toFile(new File(ud, "thumb." + filename + fileExt));
				}	
				else {
						copyInputStreamToFile(file.getInputStream(), new File(ud, filename + fileExt));
					}
				
				msg = successFlag + "文件上传成功！";
				this.uploadDir = defaultUploadDir + "/" + nextUploadDir;
			} catch (IOException e) {
				e.printStackTrace();
				msg = errorFlag + "文件上传失败！";
			}
		}
	}

	public void upload(MultipartFile file, String nextUploadDir, int FILE_TYPE) {
		String filename = UUID.randomUUID().toString().replaceAll("-", "");
		upload(file, filename, nextUploadDir, FILE_TYPE, false);
	}

	public void uploadCompress(MultipartFile file, String nextUploadDir, int FILE_TYPE) {
		String filename = UUID.randomUUID().toString().replaceAll("-", "");
		upload(file, filename, nextUploadDir, FILE_TYPE, true);
	}

	// 上传到基础目录下
	public void upload(MultipartFile file, int FILE_TYPE) {
		upload(file, "", FILE_TYPE);
	}

	// 获取文件名
	public String getFilename() {
		return filename;
	}

	// 获取扩展名
	public String getFileExt() {
		return fileExt;
	}

	// 获取文件名（包括扩展名）
	public String getFilenameAndExt() {
		return filename + fileExt;
	}

	// 获取完整相对路径（包含路径，文件名，扩展名）（相对于webapp路径）
	public String getPath() {
		return uploadDir + "/" + getFilenameAndExt();
	}

	// 获取完整真实路径（包含路径，文件名，扩展名）
	public String getRealPath() {
		return convertIntoRootPath(uploadDir) + "/" + getFilenameAndExt();
	}

	// 将输入流复制文件
	private void copyInputStreamToFile(InputStream in, File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file);
		int byteCount = 0;
		int bytesWritten = 0;

		byte buffer[] = new byte[65536];
		while ((byteCount = in.read(buffer)) != -1) {
			out.write(buffer, bytesWritten, byteCount);
		}
		in.close();
		out.close();
	}

	// 将相对于webapp的路径转为真实路径（文件的完整路径）
	public String convertIntoRealpath(String path) {
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String p;
		try {
			p = request.getSession().getServletContext().getResource("/").getPath();
			if (p != null) {
				p = p.replaceAll("\\\\", "/");
				p = p + path;
				p = p.replaceAll("^/(\\w:/)", "$1");// Windows上获取的第一个字符为"/",所以去除
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			p = null;
		}
		// String p = request.getSession().getServletContext().getRealPath(path);
		return p;
	}

	/**
	 * @描述 TODO 这里根目录：Windows指的是服务器运行所在的盘，Linux指的是系统根目录下的第一个文件夹
	 */
	public String convertIntoRootPath(String path) {
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		String p;
		try {
			p = this.getClass().getResource("/").getPath();
			if (p != null) {
				p = p.replaceAll("\\\\", "/");
				p = p.replaceAll("^(/\\w+/).*", "$1").replaceAll("^/(\\w:/).*", "$1");
				p = p + path;
			}
		} catch (Exception e) {
			e.printStackTrace();
			p = null;
		}
		return p;
	}

	public void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			myDelFile.delete();
		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();
		}
	}

	public void copyFile(String oldPath, String newPath) throws IOException {

		FileInputStream ins = new FileInputStream(new File(oldPath));
		FileOutputStream out = new FileOutputStream(new File(newPath));
		byte[] b = new byte[1024];
		int n = 0;
		while ((n = ins.read(b)) != -1) {
			out.write(b, 0, n);
		}

		ins.close();
		out.close();
	}

	public static void main(String g[]) {
		String p = "/C:/Users/";
		p = "/bas/dfsf/fsdf/";
		p = p.replaceAll("^(/\\w+/).*", "$1").replaceAll("^/(\\w:/).*", "$1");
		System.out.println(p);
	}
}
