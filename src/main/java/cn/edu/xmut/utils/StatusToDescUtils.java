package cn.edu.xmut.utils;

/**
 * @描述 TODO 通过状态获取状态对应的描述
 * @作者	陈财
 * @创建时间 2015年2月12日 下午1:30:10
 */
public class StatusToDescUtils {
	/**
	 * 状态的描述
	 */
	private static String desc="未知状态";

	/**
	 * 
	 * @描述 TODO 获取订单描述
	 * @作者         陈财
	 * @创建时间 2015年2月12日 下午1:36:24
	 */
	public static String getDingDanDesc(int status){
		desc="未知状态";
		switch(status){
		case -2:
			desc="已退单";
			break;
		case -1:
			desc="估值与实际不符";
			break;
		case 0:
			desc="已下单";
			break;
		case 1:
			desc="已检测完毕";
			break;
		case 2:
			desc="已选择中标合作商";
			break;
		case 3:
			desc="已确认评价";
			break;
		}
		return desc;
	}
	
	/**
	 * 
	 * @描述 TODO 获取估值描述
	 * @作者         陈财
	 * @创建时间 2015年2月12日 下午1:37:15
	 */
	public static String getEvaluateDesc(int status){
		desc="未知状态";
		switch(status){
		case -6:
			desc="以旧换新，购物车";
			break;
		case -5:
			desc="已投废";
			break;
		case -4:
			desc="已退单";
			break;
		case -3:
			desc="估值与实际不符";
			break;
		case -2:
			desc="游客估值";
			break;
		case -1:
			desc="登陆用户估值";//进入环保回收箱
			break;
		case 0:
			desc="已下单";
			break;
		case 1:
			desc="已检测完毕";
			break;
		case 2:
			desc="已选择中标合作商";
			break;
		case 3:
			desc="已确认评价";
			break;
		}
		return desc;
	}
	
	/**
	 * 
	 * @描述 TODO 获取报价状态描述
	 * @作者         陈财
	 * @创建时间 2015年2月12日 下午1:37:15
	 */
	public static String getQuoteDesc(int status){
		desc="未知状态";
		switch(status){
		case 0:
			desc="预修改报价";
			break;
		case 1:
			desc="成功报价";
			break;
		case 2:
			desc="不可用报价";
			break;
		}
		return desc;
	}
}
