package cn.edu.xmut.core.entity;

public final class Regex{
	public static final String 年="^\\d{4}$";
	public static final String 年月日="^\\d{4}-\\d{2}-\\d{2}$";
	public static final String 年月日时分秒="^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$";
	public static final String 学期="^[1-3]{1}$";
	public static final String 英文="^[a-zA-Z]{1,18}$";
	public static final String 学校英文简称=英文;
	public static final String 布尔值="^true|false$";
	public static final String 学号="^[0-9]{8,18}$";
	public static final String 学生账号="^[a-z]{1,18}[0-9]{8,18}$";
	public static final String 文本="^.{1,18}$";
	public static final String 大文本="^(.|\\s){1,10000}$";
	public static final String 不带空格文本="^(.|\\S){1,18}$";
	public static final String 不带空格标题="^(.|\\S){1,30}$";  //yangzj
	public static final String 年份="^[0-9]{4}$";
	public static final String 数字验证码=年份;
	public static final String 验证码="^[a-zA-Z0-9]{4}$";
	public static final String 密码="^.{6,18}$";
	public static final String 中文名="^[\u4e00-\u9fa5]{2,8}$";
	public static final String 姓名="^([\u4e00-\u9fa5]{2,8}|([a-zA-Z]+\\s?){4,30})$";
	public static final String 手机号码="^(13|15|18)\\d{9}$";
	public static final String 邮箱="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
	public static final String 账号="^("+学生账号+")|("+手机号码+")|("+邮箱+")|("+姓名+")$";
	public static final String 扣扣="[0-9]{6,18}";
	public static final String 日期="^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$";
	public static final String 短日期="^(\\d{4})-(\\d{2})-(\\d{2})$";
	public static final String 主键="^\\w{30,}$";
	public static final String 登陆类型="^loginName|loginPhone|loginEmail|studentNo$";
	public static final String 文件名="^.{1,50}$";
	public static final String 正整数="^[1-9][0-9]*$";
	public static final String 整数="^[0-9][0-9]*$";
	public static final String 带下划线英文="^[a-zA-Z_]{1,18}$";
	public static final String 描述="^(.|\\s){1,255}$";
	public static final String 网站地址 = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";
	
	public static final String 正浮点数 = "^\\d+(\\.\\d+)?$";
}
