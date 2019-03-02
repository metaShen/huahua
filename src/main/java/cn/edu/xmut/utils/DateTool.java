package cn.edu.xmut.utils;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class DateTool {
	
	private static SimpleDateFormat longFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@SuppressWarnings("unused")
	private static SimpleDateFormat shortFormat=new SimpleDateFormat("yyyy-MM-dd");
	
	public static String LongFormat = "yyyy-MM-dd HH:mm:ss";
	public static String ShortFormat = "yyyy-MM-dd";
	public static String ShortFormatEx = "yyyyMM";
	public static String ShortFormatExx = "yyyy-MM";
	public static String LongFormatEx = "yyyyMMddHHmmss";
	public static String SqlServerXmlFormat = "yyyy-MM-dd'T'HH:mm:ss";
	
	/**
	 * 
	 * @描述  判断当前日期是星期几  返回數字  1-7 對應 週一到周天
	 * @请求参数
	 * String pTime 日期   YYYY-MM-DD
	 * @作者  chrimer(林江毅)
	 * @创建时间 2015年4月15日 下午10:47:56
	 */
	public static int dayToWeekInt(String pTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}
	/**
	 * 
	 * @描述  判断当前日期是星期几  返回字符串
	 * @请求参数
	 * String pTime 日期   YYYY-MM-DD
	 * @作者  chrimer(林江毅)
	 * @创建时间 2015年4月15日 下午10:47:56
	 */
	public static String dayToWeekString(String pTime) throws Exception {
		int dayToWeek = dayToWeekInt(pTime);
		String result = "不確定的日期";
		
		switch(dayToWeek){
		case 1:
			result = "星期一";
			break;
		case 2:
			result = "星期二";
			break;
		case 3:
			result = "星期三";
			break;
		case 4:
			result = "星期四";
			break;
		case 5:
			result = "星期五";
			break;
		case 6:
			result = "星期六";
			break;
		case 7:
			result = "星期天";
			break;
		}
		
		return result;
	}
	/**
	 * 获得time的validate 天之前的时间
	 * 
	 * @author chrimer
	 * @version 2013-12-20 上午10:26:28
	 * @param time  yyyy-MM-dd HH:mm:ss
	 * @param validate  30
	 * @return
	 */
	public static String getLastTime(String time,int validate) {
		
		DateFormat df=new SimpleDateFormat(LongFormat);
		Calendar c1=Calendar.getInstance();
		
		try
		{
			c1.setTime(df.parse(time));
			c1.add(Calendar.DATE,validate*(-1));
		}catch(java.text.ParseException e){
			System.err.println("格式不正确");
			return "";
		}
		return df.format(c1.getTime());
	}
	/**
	 * 获得day的validate 天之前的日期
	 * 
	 * @author chrimer
	 * @version 2013-12-20 上午10:26:28
	 * @param day  yyyy-MM-dd
	 * @param validate  30
	 * @return
	 */
	public static String getLastDay(String day,int validate) {
		
		DateFormat df=new SimpleDateFormat(ShortFormat);
		Calendar c1=Calendar.getInstance();
		
		try
		{
			c1.setTime(df.parse(day));
			c1.add(Calendar.DATE,validate*(-1));
		}catch(java.text.ParseException e){
			System.err.println("格式不正确");
			return "";
		}
		return df.format(c1.getTime());
	}
	/**
	 * 		
	 * @描述  计算time和当前时间相差几天
	 * @作者  chrimer(林江毅)
	 * @创建时间 2015年3月5日 上午10:44:24
	 */
	public static Long countDay(String time) {
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = f.format(calendar.getTime());
		
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		try
		{
			c1.setTime(df.parse(time));
			c2.setTime(df.parse(nowTime));
		}catch(java.text.ParseException e){
			System.err.println("格式不正确");
		}
		Long day = (c2.getTimeInMillis()-c1.getTimeInMillis())/1000/60/60/24;
		return day;
	}

	public static String getValid(String time,Long iValid){
		Long iDay = countDay(time);
		if(iDay>=iValid){
			return "已过期";
		}else{
			return "还有"+(iValid-iDay)+"天过期";
		}
	}
	
	/**
     * 用指定的时间来计算时间差
     *
     * @param startTimeStr String
     * @param endTimeStr String
     * @return String
     */
	public static String countMinute(String startTimeStr, String endTimeStr){
		Timestamp startTime = DateTool.StringToTimestamp(startTimeStr, LongFormat);
		Timestamp endTime = DateTool.StringToTimestamp(endTimeStr, LongFormat);
		long startTimeMillis = startTime.getTime();
		long endTimeMillis = endTime.getTime();
		long sum = (endTimeMillis - startTimeMillis) / 60000; // 毫秒与分钟的进率为60000
		long hour = sum / 60;
		long minute = sum % 60;
		long day = 0;
		if(hour >= 24){
			day = hour / 24;
		}
		String dayStr = Long.toString(day) + "天";
		String hourStr = Long.toString(hour - (day * 24)) + "小时";
		String minuteStr = Long.toString(minute) + "分钟";
		String result = dayStr + hourStr + minuteStr;
		return result;
	}
	
	
	public static String getFriendTime(String strTime) {
		String friendTime = "";
		Timestamp time = DateTool.StringToTimestamp(strTime, LongFormat);
		long currentMillies = System.currentTimeMillis();
		long timeMillies = time.getTime();
		long result = (currentMillies - timeMillies) / 60000;
		long temp = result / 60;
		if(temp == 0){
			friendTime = "大约"+String.valueOf(result+1)+"分钟之前";
		} else if(temp > 0 && temp < 24) {
			friendTime = "大约"+String.valueOf(temp)+"小时之前";
		} else {
			friendTime = getShortDate(strTime);
		}
		return friendTime;
	}
	/**
     * 用指定的时间来判断时间是否有效
     *
     * @param startTimeStr String
     * @param endTimeStr String
     * @return Boolean
     */
	public static Boolean isValid(String startTimeStr, String endTimeStr){
		Timestamp startTime = DateTool.StringToTimestamp(startTimeStr, LongFormat);
		Timestamp endTime = DateTool.StringToTimestamp(endTimeStr, LongFormat);
		long startTimeMillis = startTime.getTime();
		long endTimeMillis = endTime.getTime();
		if((endTimeMillis - startTimeMillis) > 0){
			return true;
		} else {
			return false;
		}
	}
	
	/**
     * 用指定的时间来来计算星期几
     *
     * @param startTimeStr String
     * @param endTimeStr String
     * @return int week ("1")
     */
	public static int getIntegerWeekDay(String DateStr){
	      SimpleDateFormat formatYMD=new SimpleDateFormat("yyyy-MM-dd");//formatYMD表示的是yyyy-MM-dd格式
	      SimpleDateFormat formatD=new SimpleDateFormat("E");//"E"表示"day in week"
	      Date d=null;
	      int week = 0;
	      try{
	         d=formatYMD.parse(DateStr);//将String 转换为符合格式的日期
	         String weekDay=formatD.format(d);
	         if(weekDay.equals("星期日")){
	        	 week = 7;
	         } else if(weekDay.equals("星期一")) {
	        	 week = 1;
	         } else if(weekDay.equals("星期二")) {
	        	 week = 2;
	         } else if(weekDay.equals("星期三")) {
	        	 week = 3;
	         } else if(weekDay.equals("星期四")) {
	        	 week = 4;
	         } else if(weekDay.equals("星期五")) {
	        	 week = 5;
	         } else if(weekDay.equals("星期六")) { 
	        	 week = 6;
	         }
	      }catch (Exception e){
	         e.printStackTrace();
	      }
	     return week;
	 }
	
	/**
     * 用指定的时间来来计算星期几
     *
     * @param startTimeStr String
     * @param endTimeStr String
     * @return String weekDay ("星期一")
     */
	public static String getStrWeekDay(String DateStr){
	      SimpleDateFormat formatYMD=new SimpleDateFormat("yyyy-MM-dd");//formatYMD表示的是yyyy-MM-dd格式
	      SimpleDateFormat formatD=new SimpleDateFormat("E");//"E"表示"day in week"
	      Date d=null;
	      String weekDay = "";
	      try{
	         d = formatYMD.parse(DateStr);//将String 转换为符合格式的日期
	         weekDay = formatD.format(d);
	      }catch (Exception e){
	         e.printStackTrace();
	      }
	     return weekDay;
	 }
	
	/**
     * 以指定的格式来格式化日期
     *
     * @param date Date
     * @param format String
     * @return String
     */
    public static String DateToString(java.util.Date date,String format)
    {
        String result = "";
        if(date != null)
        {
            try
            {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    public static String TimstampToString(Timestamp ts,String format)
    {
        String result = "";
        if(ts != null)
        {
            try
            {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(ts);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * String转化为java.sql.date类型，
     * @param strDate
     * @return
     */
    public static java.sql.Date  StringToDate(String strDate,String format){
         if(strDate != null && !strDate.equals("")) {
             try{
                   SimpleDateFormat formatter= new SimpleDateFormat (format);
                   java.util.Date  d = formatter.parse(strDate);
                   java.sql.Date  numTime=new  java.sql.Date(d.getTime());
                   return numTime;
             }catch(Exception e){
                   return null;
             }
         }else {
             return null;
         }
    }
    
    public static java.sql.Timestamp  StringToTimestamp(String strDate,String format){
        if(strDate != null && !strDate.equals("")) {
            try{
                  SimpleDateFormat formatter= new SimpleDateFormat (format);
                  java.util.Date  d = formatter.parse(strDate);
                  java.sql.Date  numTime=new  java.sql.Date(d.getTime());
                  return new java.sql.Timestamp(numTime.getTime());
            }catch(Exception e){
                  return null;
            }
        }else {
            return null;
        }
   }
    
    public static Timestamp getNowTimestamp(){
        java.util.Date  d = new java.util.Date();
        Timestamp numTime=new Timestamp(d.getTime());
        return numTime;
	}
    
    public static String getTickCount()
	{
		return Long.toString(System.currentTimeMillis());
	}
    
    
	
	/**返回当前时间
	 * 
	 * @return HH:mm:ss
	 */
	public static String  getCurrTime(){
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat f=new SimpleDateFormat("HH:mm:ss");
		return f.format(calendar.getTime());
	}
	/**返回当前时间
	 * 
	 * @return HH:mm:ss:fff
	 */
	public static String  getCurrTime_1(){
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat f=new SimpleDateFormat("HH:mm:ss");

		return f.format(calendar.getTime()) + ":" + calendar.get(Calendar.MILLISECOND);
	}
	 /**返回当前日期
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String  getCurrDate(){
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		return f.format(calendar.getTime());
	}
	
	/**返回当前时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String  getCurrDateTime(){
		Calendar calendar=Calendar.getInstance();
		return longFormat.format(calendar.getTime());
	}
	public static String  getDateTime(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		return longFormat.format(calendar.getTime());
	}
	/**返回当前时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss:fff
	 */
	public static String  getCurrDateTime_1(){
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return f.format(calendar.getTime()) + ":" + calendar.get(Calendar.MILLISECOND);
	}
	
	
	
	/**时间转换：把秒改为:h时m分s秒
	 * 
	 */
	public static String getTimeString(long v_iSecond){
		if(v_iSecond<60){
			return v_iSecond + "秒";
		}
		long iHour,iMinute,iSenond,iHourRemain;
		String result="";
		iHour = v_iSecond / 3600;
		iHourRemain = v_iSecond % 3600;
		iMinute = iHourRemain / 60;
		iSenond = iHourRemain % 60;
		if (iHour != 0){
			result += iHour + "小时";
		}
		result += iMinute + "分";
		result += iSenond + "秒";

		return result;
	}
	public static long  getMonDayTime(Date date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		try {
			return sdf.parse(sdf.format(date)).getTime();
		} catch (ParseException e) {
			return 0;
		}
	}
	public static String getShortDate(String dateString){
		try {
			SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
			long date = sp.parse(dateString).getTime();
			long  newDate = sp.parse(sp.format(new Date())).getTime();
			int c = (int)(date-newDate)/1000/60/60/24;
			switch (c) {
			case -2:
				return "前天";
			case -1:
				return "昨天";
			case 0:
				return "今天";
			case 1:
				return "明天";
			case 2:
				return "后天";
			default:
				sp=new SimpleDateFormat("MM月dd日");
				return sp.format(new Date(date));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
