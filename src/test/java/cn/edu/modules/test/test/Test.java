package cn.edu.modules.test.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
  public static void main(String[] args) throws ParseException{
	  String time = "2012-12-12 12:12:12";
	  System.out.println(time.substring(0, 10));
//	  Date date = new Date(2013,7,6);
//	  Date date1  = new Date(2013,2,24);
//     long hah = 	          date.getTime()-date1.getTime();
//    long aa =  hah/(1000*60*60*24);
//    
//     System.out.print(aa/7);
	  
	  Calendar cal = Calendar.getInstance();
	  System.out.println("今天的日期: " + cal.getTime());
//
//	  int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
//	  cal.add(Calendar.DATE, -day_of_week);
//	  System.out.println("本周第一天: " + cal.getTime());
//
//	  cal.add(Calendar.DATE, 6);
//	  System.out.println("本周末: " + cal.getTime());
	  
	  int day_of_week =cal.get(Calendar.WEEK_OF_YEAR);
	  String s="20130225";
	  DateFormat df=new SimpleDateFormat("yyyyMMdd");
	  Calendar cal1 = Calendar.getInstance();
	  Date date1  =df.parse(s);
	  cal1.setTime(date1);
	  System.out.println("过去的日期: " + cal1.getTime());
	  int day_of_week1 =cal1.get(Calendar.WEEK_OF_YEAR);
	  
	  int hah= day_of_week - day_of_week1;
	  System.out.println(hah);
	  
	  
  }
}
