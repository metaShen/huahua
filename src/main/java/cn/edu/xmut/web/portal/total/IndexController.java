package cn.edu.xmut.web.portal.total;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.xmut.web.SummerBaseController;

@Controller("indexTotalController")
public class IndexController  extends SummerBaseController{
	@RequestMapping("/total")
	public String html(HttpServletRequest request) {
		String src = "/total/"+getPath(request);
		return src;
	}
}
