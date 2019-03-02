package cn.edu.xmut.web.portal.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.xmut.web.SummerBaseController;

@Controller("indexUserController")
public class IndexController  extends SummerBaseController{
	@RequestMapping("/user")
	public String html(HttpServletRequest request) {
		String src = "/user/"+getPath(request);
		return src;
	}
}
