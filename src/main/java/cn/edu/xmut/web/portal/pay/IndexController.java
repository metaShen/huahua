package cn.edu.xmut.web.portal.pay;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.xmut.web.SummerBaseController;

@Controller("indexPayController")
public class IndexController  extends SummerBaseController{
	@RequestMapping("/pay")
	public String html(HttpServletRequest request) {
		String src = "/pay/"+getPath(request);
		return src;
	}
}
