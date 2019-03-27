package cn.edu.xmut.web;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.xmut.modules.user.service.UserService;
import cn.edu.xmut.utils.Constants;
import cn.edu.xmut.utils.UtilCtrl;

@Controller("indexController")
@RequestMapping(value = "")
public class IndexController extends SummerBaseController{
	@Resource(name = "userServiceImpl")
	private UserService userService;
    /*
     * 公共登录
     */
	@RequestMapping("/login")
	public String index(HttpServletRequest request) {
		return "/login";
	}
	
	/*
	 * 获取用户微信信息
	 * 1.用code换取accessToken和openId
	 * 2.用accessToken和openId获取unionID和用户详细信息
	 * 获取成功后，将wxUser存入session
	 */
	
	
	@RequestMapping("/reg")
	public String reg(HttpServletRequest request){
		return "/reg2";
	}
	@RequestMapping("/index")
	public String ind(HttpServletRequest request){
		return "/index";
	}
	
	@RequestMapping("/500")
	public String error500(HttpServletRequest request){
		return "/500";
	}
	
	@RequestMapping("/404")
	public String error404(HttpServletRequest request){
		return "/404";
	}
	
	
}
