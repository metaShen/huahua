package cn.edu.xmut.modules.weixin.handler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Component;

import cn.edu.xmut.modules.user.bean.User;
import cn.edu.xmut.modules.user.service.impl.UserServiceImpl;
import cn.edu.xmut.modules.weixin.WeixinService;
import cn.edu.xmut.modules.weixin.builder.TextBuilder;
import cn.edu.xmut.utils.Constants;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

/**
 * @author Binary Wang
 */
@Component
public class MsgHandler extends AbstractHandler {
	@Resource(name = "WeixinService")
	private WeixinService wxService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		String openId = wxMessage.getFromUser();
		WxMpUser wxMpUser = wxService.getUserService().userInfo(openId);
		String userName = wxMpUser.getNickname();
		if("你好".equals(wxMessage.getContent())) {
			return new TextBuilder().build(userName+",你好", wxMessage, wxService);
		}		
		else if("登录".equals(wxMessage.getContent())) {
			return new TextBuilder().build(userName+ ",http://lkh.frp.yxd.ink/book/login.jhtml", wxMessage, wxService);
		}
		else if ("再见".equals(wxMessage.getContent())) {
			return new TextBuilder().build(userName+",欢迎再来", wxMessage, wxService);
		}else {
			return new TextBuilder().build(userName+",在的，你说", wxMessage, wxService);
		}
	}

}
