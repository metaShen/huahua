/**
 * 
 */
package cn.edu.modules.test.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.w3c.dom.NodeList;

/**
 * @author yangzj
 * @version 2014-9-12 上午11:18:31
 */
public class SoapTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String lineTxt = null;
		try {
			String filePath = "d:\\test.txt";
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String content = "";
				while ((lineTxt = bufferedReader.readLine()) != null) {
					content = content + lineTxt;
				}
				read.close();
				String xml = sendSoap("", content);

				Document doc = null;
				try {

					// 读取并解析XML文档

					// SAXReader就是一个管道，用一个流的方式，把xml文件读出来

					// SAXReader reader = new SAXReader();
					// //User.hbm.xml表示你要解析的xml文档

					// Document document = reader.read(new
					// File("User.hbm.xml"));

					// 下面的是通过解析xml字符串的

					doc = DocumentHelper.parseText(xml); // 将字符串转为XML

					Element rootElt = doc.getRootElement(); // 获取根节点

					System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称


					Iterator<?> iterss = rootElt.elementIterator("Body"); // /获取根节点下的子节点body

					// 遍历body节点

					while (iterss.hasNext()) {

						Element recordEless = (Element) iterss.next();
						System.out.println("result:" + recordEless.getName());
						
						Iterator<?> business = recordEless.elementIterator("business"); 
						while (business.hasNext()) {
							Element bChild = (Element) business.next();
							System.out.println("bChild:" + bChild.getName());
							
							Iterator<?> resultset = bChild.elementIterator("resultset"); 
							while (resultset.hasNext()) {
								Element rChild = (Element) resultset.next();
								System.out.println("rChild:" + rChild.getName() +"rChild name"+rChild.attributeValue("name"));
								if(rChild.attributeValue("name").equals("retrieve")) {
									Iterator<?> row = rChild.elementIterator("row"); 
									while (row.hasNext()) {
										Element rowChild = (Element) row.next();
										rowChild.getNodeTypeName();
										List<Attribute> list = rowChild.attributes();
										System.out.println("eName();:" + list);
											
										for(int i=0;i<list.size();i++) {
											System.out.println("Name();:" + list.get(i).getName()+"value():" +list.get(i).getValue());
										}
									}
								}
							}
						}
						
					}
				} catch (DocumentException e) {
					e.printStackTrace();

				} catch (Exception e) {
					e.printStackTrace();

				}

			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	}

	public static String sendSoap(String urlStr, String p) throws SOAPException {
		urlStr = "http://222.76.243.149:9010/esb/esbproxy";
		StringBuffer result = new StringBuffer();
		try {
			String reqXml = p;

			URL url = new URL(urlStr);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();

			// 设置 http发送相关属性
			httpConn.setRequestProperty("Content-Length",
					String.valueOf(reqXml.getBytes().length));
			httpConn.setRequestProperty("Content-Type",
					"text/xml; charset=UTF-8");
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			// 写消息
			OutputStream out = httpConn.getOutputStream();
			out.write(reqXml.getBytes());
			out.close();
			// 读取结果
			
			
			InputStreamReader isr = new InputStreamReader(
					httpConn.getInputStream(), "gb2312");
			BufferedReader in = new BufferedReader(isr);
			String inputLine = in.readLine();
			
			while (null != inputLine) {
				result.append(inputLine);
				inputLine = in.readLine();
			}
			
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
//			DocumentBuilder db = dbf.newDocumentBuilder(); 
//			org.w3c.dom.Document document = db.parse(out);
////			String text = document.getElementsByTagName("para").toString();
//			NodeList list = document.getElementsByTagName("retrieve");  
//			System.out.println("ddddd"+list.item(0).getNodeValue());
			
			System.out.println(result.toString()); // 打印数据
			// return result.toString();
		} catch (Exception e) {
			throw new SOAPException("发送SOAP信息失败,失败原因如下:" + e.getMessage());
		}
		return result.toString();
	}

}
