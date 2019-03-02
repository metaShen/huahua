package cn.edu.xmut.utils;

import cn.edu.xmut.core.persistence.Pageable;

public class PageUtils {
	/**
	 * 
	* 获得分页数据
	* @return Pageable
	* @author chrimer(林江毅)
	* @date 2015年1月31日 下午9:55:53
	 */
	public static Pageable getPageable() {
		int pageNumber = UtilCtrl.getRequestIntValueWithDefault("pageNumber", 1);
		int pageSize = UtilCtrl.getRequestIntValueWithDefault("pageSize", 10);
		Pageable pageable = new Pageable(pageNumber, pageSize);
		
		return pageable;
	}

}
