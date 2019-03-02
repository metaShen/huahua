package cn.edu.xmut.utils;

import java.text.DecimalFormat;

public class CommonUtils {
	//private final static float TOLARANCE = 0.0000001F;
	private final static double DBTOLARANCE = 0.0000001;
	
	/**
	 * 
	 * @描述  TODO请用一段话对该方法进行说明
	 * @url  
	 * @请求参数
	 * String username 用户名
	 * @作者  chrimer(林江毅)
	 * @创建时间 2015年5月14日 下午5:02:05
	 */
	public static String getEducation(int iEducation){
		String result = "未知教育类型";
		switch(iEducation){
			case 1:
			{				
				result = "大专";
				break;
			}
			case 2:
			{				
				result = "本科";
				break;
			}
			case 3:
			{				
				result = "硕士";
				break;
			}
			case 5:
			{				
				result = "其他";
				break;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @描述  比较两个浮点数是否相等
	 * @请求参数
	 * float f1
	 * float f2
	 * @作者  chrimer(林江毅)
	 * @创建时间 2015年4月6日 下午10:21:05
	 */
	/*public static boolean comFloat(float f1,float f2){
		if(Math.abs(f1-f2)<=TOLARANCE){
			return true;
		}else{
			return false;
		}
	}*/
	public static boolean comDouble(double db1,double db2){
		if(Math.abs(db1-db2)<=DBTOLARANCE){
			return true;
		}else{
			return false;
		}
	}
	
	public static int getMaxDay(String yearmonth){
		String[] aryYearmonth = yearmonth.split("-");
		if(aryYearmonth.length<=1){
			return 31;
		}
		
		int year = Integer.parseInt(aryYearmonth[0]);
		String month = aryYearmonth[1];
		
		if( "01".equals(month) || "03".equals(month)
		 || "05".equals(month) || "07".equals(month)
		 || "08".equals(month) || "10".equals(month)
		 || "12".equals(month)){
			return 31;
		}else if("02".equals(month)){
			if(year%400==0 || year%4==0&&year%100!=0){
				return 29;
			}else{
				return 28;
			}
		}else{
			return 30;
		}
	}
	/**
	 * 
	 * @描述  将f 修改为只有 count位小数位
	 * @请求参数
	 * float  f 浮点数
	 * int count 小数位 位数
	 * @作者  chrimer(林江毅)
	 * @创建时间 2015年4月6日 下午9:38:43
	 */
	/*public static float changeFloat(float f,int count){
		if(count<=0){
			return f;
		}
		String format = ".";
		for(int i=1;i<=count;i++){
			format += "0";
		}
		DecimalFormat decimalFormat=new DecimalFormat(format);//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String strFloat=decimalFormat.format(f);//format 返回的是字符串
		
		return Float.parseFloat(strFloat);
	}*/
	/**
	 * 
	 * @描述  将f 修改为只有 count位小数位
	 * @请求参数
	 * float  f 浮点数
	 * int count 小数位 位数
	 * @作者  chrimer(林江毅)
	 * @创建时间 2015年4月6日 下午9:38:43
	 */
	public static double changeDouble(double db,int count){
		if(count<=0){
			return db;
		}
		String format = ".";
		for(int i=1;i<=count;i++){
			format += "0";
		}
		DecimalFormat decimalFormat=new DecimalFormat(format);//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String strDouble=decimalFormat.format(db);//format 返回的是字符串
		
		return Double.parseDouble(strDouble);
	}
	/**
	 * 
	 * @描述  通过能源类型编号，获取对应的具体类型
	 * @说明 1原油 2柴油 3汽油  4清洁柴油  5煤炭
	 * @请求参数
	 * int iType 类型编号
	 * @作者  chrimer(林江毅)
	 * @创建时间 2015年2月3日 下午2:23:33
	 */
	public static String getEnergyType(int iType){
		String result = "未知类型";
		switch(iType){
		case 1:
			result = "原油";
			break;
		case 2:
			result = "柴油";
			break;
		case 3:
			result = "汽油";
			break;
		case 4:
			result = "清洁柴油";
			break;
		case 5:
			result = "煤炭";
			break;
		}
		
		return result;
	}

	/**
	 * 
	 * @描述  通过原油类型编号，获取对应的原油类型
	 * @说明 1英国布伦特 2美国国际原油 
	 * @请求参数
	 * int iType 类型编号
	 * @作者  chrimer(林江毅)
	 * @创建时间 2015年2月3日 下午2:23:33
	 */
	public static String getYyType(int iType){
		String result = "未知类型";
		switch(iType){
		case 1:
			result = "英国布伦特";
			break;
		case 2:
			result = "美国国际原油 ";
			break;		
		}
		
		return result;
	}
	/**
	 * 截取字符串 没有后缀
	 * @param s
	 * @param length 截取的字符串字节长度
	 * @return 截取的字符串+后缀
	 * @throws Exception
	 * Unicode编码：1-0x0031，啊-0x554a
	 */
	public static String getSubstring(String s, int length) throws Exception{
		return getSubstring(s, length,"");
	}
	/**
	 * 截取字符串
	 * @param s
	 * @param length 截取的字符串字节长度
	 * @param suffix 后缀
	 * @return 截取的字符串+后缀
	 * @throws Exception
	 * Unicode编码：1-0x0031，啊-0x554a
	 */
	public static String getSubstring(String s, int length,String suffix) throws Exception  {  
		
		byte[] bytes = s.getBytes("Unicode");  
	    int n = 0; // 表示当前的字节数  
	    int i = 2; // 要截取的字节数，从第3个字节开始  
	    if(bytes.length-2 <= length) return s;
	    for (; i < bytes.length && n < length; i++)  
	    {  
	        // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节  
	        if (i % 2 == 1)  
	        {  
	            n++; // 在UCS2第二个字节时n加1  
	        }else 
	        {  
	            // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节  
	            if (bytes[i] != 0)  
	            {  
	                n++;  
	            }  
	        }  
	    }  
	    // 如果i为奇数时，处理成偶数  
	    if (i % 2 == 1)  
	    {  
	        // 该UCS2字符是汉字时，去掉这个截一半的汉字  
	        if (bytes[i - 1] != 0)  
	            i = i - 1;  
	        // 该UCS2字符是字母或数字，则保留该字符  
	        else 
	            i = i + 1;  
	    }  
	    String temp = new String(bytes, 0, i, "Unicode")+ suffix;
	    return temp;  
	}  
}	
