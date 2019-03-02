package cn.edu.xmut.web.portal.account;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.xmut.web.SummerBaseController;

@Controller("indexAccountController")
public class IndexController  extends SummerBaseController{
	@RequestMapping("/account")
	public String html(HttpServletRequest request) {
		String src = "/account/"+getPath(request);
		return src;
	}
}
