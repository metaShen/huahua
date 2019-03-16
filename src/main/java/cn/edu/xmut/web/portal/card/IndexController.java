package cn.edu.xmut.web.portal.card;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.xmut.web.SummerBaseController;

@Controller("indexCardController")
public class IndexController  extends SummerBaseController{
	@RequestMapping("/card")
	public String html(HttpServletRequest request) {
		String src = "/card/"+getPath(request);
		return src;
	}
}
