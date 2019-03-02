package cn.edu.xmut.core.filter.permission;

public enum PermissionTypeEnum {

	//角色：前台用户（用户可能升级成 合作商或者加盟商）
	/**
	 * 前台用户 权限，只有前台用户（用户可能升级成 合作商或者加盟商）才有这个权限
	 */
	FRONT,     
	
	//角色：所有后台角色
	/**
	 * 后台管理系统登录权限
	 */
	ADMINLOGIN,     
	
	//角色：普通管理员
	/**
	 * 用户管理、角色管理、权限管理，还有他们之间的管理
	 */
	URP,     
	/**
	 * 返点规则
	 */
	FDRULE,     
	/**
	 * 积分规则
	 */
	JFRULE,     
	/**
	 * 报价时间设置
	 */
	QUOTETIME,     
	/**
	 * 审核合作商、加盟商申请
	 */
	VALIDATE,     
	/**
	 * 手机（pad）类别、一级、二级参数设置
	 */
	ITEMSET,     
	/**
	 * 常用设置：报价有效期、报价合作商最多个数、估值有效期等
	 */
	SYSTEMSET,
	/**
	 * 新机报价
	 */
	QUOTENEW,   
	//角色：数据录入人员
	/**
	 * 常用设置：网站软文
	 */
	COMMONSET,     
	/**
	 * 缺省评价管理
	 */
	DEFAULTCOMMENT,     
	/**
	 * 帮助文档
	 */
	HELP,     
	/**
	 * 捐赠
	 */
	CONTRIBUTE,     
	/**
	 * 手机（pad）品牌、型号录入
	 */
	ITEMADD,     	
	
	//角色：订单管理人员
	/**
	 * 旧机回收、以旧换新 订单管理
	 */
	DINGDAN,     
	
	
	//角色：合作商
	/**
	 * 旧机报价
	 */
	QUOTE,     
	/**
	 * 查看我的订单
	 */
	MYDINGDAN   
	
}
