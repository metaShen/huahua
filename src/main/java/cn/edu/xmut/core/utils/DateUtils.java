/**
 * 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.edu.xmut.core.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;


/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author lilin
 * @version 2013-3-15
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {
	
	public static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyyMMdd）
	 */
	public static String getToday() {
		return getDate("yyyyMMdd");
	}
	/**
	 * 得到当前日期时间字符串 格式（yyyyMMddhhmmss）
	 */
	public static String getNow() {
		return getDate("yyyyMMddhhmmss");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}
	
	/**
	 * 
	 * @param parsePatterns
	 * @return
	 */
	public static String getpastDateTime(String parsePatterns) {
		Date date = new Date();//当前日期
		SimpleDateFormat sdf = new SimpleDateFormat(parsePatterns);//格式化对象
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		calendar.add(Calendar.MONTH, -1);//月份减一
		String pastDateTime =sdf.format(calendar.getTime());
		return pastDateTime;
	}
	
	/**
	 * 	
	 * @param day
	 * @param parsePatterns
	 * @return
	 */
	public static String getpastDateTimeByDate(int day, String parsePatterns) {
		Date date = new Date();//当前日期
		SimpleDateFormat sdf = new SimpleDateFormat(parsePatterns);//格式化对象
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		calendar.add(Calendar.DATE, -day);//天数减一
		String pastDateTime =sdf.format(calendar.getTime());
		return pastDateTime;
	}
	
	/**
	 * 
	 * @param month
	 * @param parsePatterns
	 * @return
	 */
	public static String getpastDateTimeByMonth(int month, String parsePatterns) {
		Date date = new Date();//当前日期
		SimpleDateFormat sdf = new SimpleDateFormat(parsePatterns);//格式化对象
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		calendar.add(Calendar.MONTH, -month);//月份减一
		String pastDateTime =sdf.format(calendar.getTime());
		return pastDateTime;
	}
	
	/**
	 * 根据年数计算时间
	 * @param year
	 * @param parsePatterns 时间格式
	 * @return
	 */
	public static String getPastDateTimeByYear(int year, String parsePatterns) {
		Date date = new Date();//当前日期
		SimpleDateFormat sdf = new SimpleDateFormat(parsePatterns);//格式化对象
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		calendar.add(Calendar.YEAR, -year);//年份减一
		String pastDateTime =sdf.format(calendar.getTime());
		return pastDateTime;
	}
	
	/**
	 * 
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	public static String getAge(Date birthDay) { 
        Calendar cal = Calendar.getInstance(); 

        if (cal.before(birthDay)) { 
            throw new IllegalArgumentException( 
                "The birthDay is before Now.It's unbelievable!"); 
        } 

        int yearNow = cal.get(Calendar.YEAR); 
        int monthNow = cal.get(Calendar.MONTH)+1; 
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); 
        
        cal.setTime(birthDay); 
        int yearBirth = cal.get(Calendar.YEAR); 
        int monthBirth = cal.get(Calendar.MONTH); 
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH); 

        int age = yearNow - yearBirth; 

        if (monthNow <= monthBirth) { 
            if (monthNow == monthBirth) { 
                if (dayOfMonthNow < dayOfMonthBirth) { 
                    age--; 
                } 
            } else { 
                age--; 
            } 
        } 

        return age +""; 
    }
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
//		System.out.println(getpastDateTimeByMonth(5,parsePatterns[0]));
//		String t1 = "1992-05-02";
//		Date d1 = parseDate(t1);
//		Date date = new Date();
//		String t2 = getDate();
//		Date d2 = parseDate(t2);
		System.out.println(getpastDateTimeByDate(3, parsePatterns[0]));
		
	}
}
