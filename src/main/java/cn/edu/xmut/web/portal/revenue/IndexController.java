package cn.edu.xmut.web.portal.revenue;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.xmut.web.SummerBaseController;

@Controller("indexRevenueController")
public class IndexController  extends SummerBaseController{
	@RequestMapping("/revenue")
	public String html(HttpServletRequest request) {
		String src = "/revenue/"+getPath(request);
		return src;
	}
}
