/**
 * 
 */
package cn.edu.xmut.core.entity;

/**
 * @author yangzj
 * @version 2014-8-29 上午9:42:50
 */
public class Regexp {

	
	public static final String YM="^\\d{4}-\\d{2}$";
	public static final String YTD="^\\d{4}-\\d{2}-\\d{2}$";
	public static final String DATE_TIME="^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$";
	public static final String TERM="^[1-3]{1}$";
	public static final String ENGLISH="^[a-zA-Z]{1,18}$";
	public static final String SCHOOL_ABB=ENGLISH;
	public static final String BOOLEAN="^true|false$";
	public static final String STUDENT_ID="^[0-9]{8,18}$";
	public static final String STUDENT_ACCOUNT="^[a-z]{1,18}[0-9]{8,18}$";
	public static final String TEXT="^.{1,18}$";
	public static final String BIG_TEXT="^(.|\\s){1,10000}$";
	public static final String TEXT_NO_BLANK="^(.|\\S){1,18}$";
	public static final String TITLE_NO_BLANK="^(.|\\S){1,30}$";  //yangzj
	public static final String YEAR="^[0-9]{4}$";
	public static final String NUMBER=YEAR;
	public static final String SECURITY_CODE="^[a-zA-Z0-9]{4}$";
	public static final String PASSWORD="^.{6,18}$";
	public static final String CHINESE_NAME="^[\u4e00-\u9fa5]{2,8}$";
	public static final String NAME="^([\u4e00-\u9fa5]{2,8}|([a-zA-Z]+\\s?){4,30})$";
	public static final String PHONE_NUMBER="^(13|15|18)\\d{9}$";
	public static final String EMAIL="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
	public static final String QQ="[0-9]{6,18}";
	/**
	 * 日期格式：YYYY-MM-DD
	 */
	public static final String DATE="^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$";
	public static final String SHORT_DATE="^(\\d{4})-(\\d{2})-(\\d{2})$";
	public static final String UUID="^\\w{30,}$";
	public static final String LOGIN_TYPE="^loginName|loginPhone|loginEmail|studentNo$";
	public static final String FILE_NAME="^.{1,50}$";
	public static final String POSITIVE_INTEGER="^[1-9][0-9]*$";
	public static final String INTEGER="^[0-9][0-9]*$";
	public static final String ENGLIST_UNDERLINE="^[a-zA-Z_]{1,18}$";
	public static final String DESCRIBE="^(.|\\s){1,255}$";
	public static final String WEBSITE = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";
}
