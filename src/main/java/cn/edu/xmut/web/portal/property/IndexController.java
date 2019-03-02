package cn.edu.xmut.web.portal.property;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.xmut.web.SummerBaseController;

@Controller("indexPropertyController")
public class IndexController  extends SummerBaseController{
	@RequestMapping("/property")
	public String html(HttpServletRequest request) {
		String src = "/property/"+getPath(request);
		return src;
	}
}
