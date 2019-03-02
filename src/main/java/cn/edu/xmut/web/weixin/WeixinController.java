package cn.edu.xmut.web.weixin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.user.bean.User;
import cn.edu.xmut.modules.user.service.impl.UserServiceImpl;
import cn.edu.xmut.modules.weixin.WeixinService;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Controller("weixinController")
@RequestMapping("/weixin")
public class WeixinController extends BaseController {
	/*@Resource(name = "WeixinService")
	private WeixinService wxService;
	
	@Resource(name="userServiceImpl")
	private UserServiceImpl userService;
	
	@RequestMapping("/bind")
	public @ResponseBody String bind(){
		String callbackUrl = "http://lkh.frp.yxd.ink/book/weixin/getUserinfo.jhtml";
		String wxUrl = wxService.oauth2buildAuthorizationUrl(callbackUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
		System.out.println("跳转到微信服务器的URL:"+wxUrl);
		return "redirect:"+ wxUrl;
	}
/*	
	/**
	 * 判断GET还是POST 如果是GET只是简单的鉴权 如果是POST，将消息分发到其他路由
	 * @throws WxErrorException 
	 *//*
	@RequestMapping(value = "/portal",method = RequestMethod.GET)
	public @ResponseBody String authGet(@RequestParam(required = false) String signature,
			@RequestParam(required = false) String timestamp, @RequestParam(required = false) String nonce,
			@RequestParam(required = false) String echostr) throws WxErrorException {
		
		if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
			return "请求参数非法，请核实!";
		}
		if (wxService.checkSignature(timestamp, nonce, signature)) {
			return echostr;
		}
		return "非法请求";
	}
/*
	@RequestMapping(value = "/portal",method = RequestMethod.POST)
	public @ResponseBody String post(@RequestBody String requestBody, @RequestParam("signature") String signature,
			@RequestParam(required = false) String encrypt_type, @RequestParam(required = false) String msg_signature,
			@RequestParam String timestamp, @RequestParam("nonce") String nonce) {
		if (!wxService.checkSignature(timestamp, nonce, signature)) {
			return "非法请求，可能属于伪造的请求！";
		}

	    String out = null;
	    if (encrypt_type == null) {
	      // 明文传输的消息
	      WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
	      WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
	      if (outMessage == null) {
	        return "";
	      }

	      out = outMessage.toXml();
	    } else if ("aes".equals(encrypt_type)) {
	      // aes加密的消息
	      WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody,
	          this.getWxService().getWxMpConfigStorage(), timestamp, nonce, msg_signature);
	      this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
	      WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
	      if (outMessage == null) {
	        return "";
	      }

	      out = outMessage.toEncryptedXml(this.getWxService().getWxMpConfigStorage());
	    }
	    return out;
	}
/*
	protected WeixinService getWxService() {
		return this.wxService;
	}
	/*
	@RequestMapping("getJsConfig")
	public @ResponseBody JSONObject getJsConfig(String url) throws WxErrorException {
		url = url.replace("&amp;", "&");
		WxJsapiSignature wxJsApi = wxService.createJsapiSignature(url);
		return JsonTool.genSuccessMsg(wxJsApi);
	}
	/*
	@RequestMapping("/login")
	public String login() {
		//之后我们在getUserinfp接口里写获取用户信息的逻辑
		String callbackUrl = "http://lkh.frp.yxd.ink/book/weixin/wxlogin.jhtml?toUrl=http://lkh.frp.yxd.ink/book/book.jhtml";
		String wxUrl = wxService.oauth2buildAuthorizationUrl(callbackUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
		System.out.println("跳转到微信服务器的url"+wxUrl);
		return "redirect:" + wxUrl ;
	}*/
	/*
	@RequestMapping("/wxlogin")
	public String wxlogin(String code,@RequestParam(required = false)String toUrl) 
	throws WxErrorException,UnsupportedEncodingException	{
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
		//通过access token 换取用户信息
		WxMpUser wxMpUser = wxService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
		
		//以下就是获取的微信用户信息
		String openId = wxMpUser.getOpenId();
		//把当前登陆的用户获取出来
		User user = userService.getByOneField(User.FieldOfUser.OPEN__ID.name(),openId);
		if(null == user) {
			//把用户信息保存到session中，并跳转到指定页面
			return  "redirect:http://lkh.frp.yxd.ink/book/reg.jhtml";
		}else {
			UtilCtrl.sessionPut("user", user);
			toUrl = URLDecoder.decode(toUrl, "UTF-8");
			//转义
			toUrl = toUrl.replace("&amp", "&");
			return "redirect:" + toUrl ;
		}
		
	}
	@RequestMapping("/getUserinfo")
	public @ResponseBody String getUserinfo(String code) throws WxErrorException {
		//获取access_tonken
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
		//通过access_token来换取用户信息
		WxMpUser wxMpUser = wxService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
		
		//获取的微信用户信息
		String openId = wxMpUser.getOpenId();
		
		//将当前用户获取出来
		User user = UtilCtrl.sessionGet("user");
		user.setOpen_id(openId);
		userService.save(user);
		
		
		//刷新session中的user
		UtilCtrl.sessionPut("user",user);
		
		//跳转页面用户信息页
		return "redirect:http://lkh.frp.yxd.ink/book/user.jhtml";
	}*/
}
