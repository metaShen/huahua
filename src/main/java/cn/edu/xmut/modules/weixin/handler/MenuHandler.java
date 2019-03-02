package cn.edu.xmut.modules.weixin.handler;

import com.alibaba.fastjson.JSON;

import cn.edu.xmut.modules.user.bean.User;
import cn.edu.xmut.modules.user.service.impl.UserServiceImpl;
import cn.edu.xmut.modules.weixin.WeixinService;
import cn.edu.xmut.modules.weixin.WxMenuKey;
import cn.edu.xmut.modules.weixin.builder.AbstractBuilder;
import cn.edu.xmut.modules.weixin.builder.ImageBuilder;
import cn.edu.xmut.modules.weixin.builder.TextBuilder;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

import javax.annotation.Resource;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * 
 * @author Binary Wang
 *
 */
@Component
public class MenuHandler extends AbstractHandler {
	@Resource(name = "WeixinService")
	private WeixinService wxService;
	@Resource(name="userServiceImpl")
	private UserServiceImpl userService;
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
      Map<String, Object> context, WxMpService wxMpService,
      WxSessionManager sessionManager) {
    WeixinService weixinService = (WeixinService) wxMpService;

    String key = wxMessage.getEventKey();
    
   /* if(key.equals("MENU_CANLEND")) {
    	User user = userService.getByOneField(User.FieldOfUser.OPEN_ID.name(), wxMessage.getFromUser());
    	if(null == user)
    	{
    		return new TextBuilder().build("请先绑定你的微信号", wxMessage, wxService);
    	}else {
			return new TextBuilder().build(user.getName()+ ",你好。你还可以借阅  "+user.getCanLend()+ "本书籍", wxMessage, wxService);
		}
    }*/
    WxMenuKey menuKey;
    try {
      menuKey = JSON.parseObject(key, WxMenuKey.class);
    } catch (Exception e) {
      return WxMpXmlOutMessage.TEXT().content(key)
          .fromUser(wxMessage.getToUser())
          .toUser(wxMessage.getFromUser()).build();
    }

    AbstractBuilder builder = null;
    switch (menuKey.getType()) {
    case XmlMsgType.TEXT:
      builder = new TextBuilder();
      break;
    case XmlMsgType.IMAGE:
      builder = new ImageBuilder();
      break;
    case XmlMsgType.VOICE:
      break;
    case XmlMsgType.VIDEO:
      break;
    case XmlMsgType.NEWS:
      break;
    default:
      break;
    }

    if (builder != null) {
      try {
        return builder.build(menuKey.getContent(), wxMessage, weixinService);
      } catch (Exception e) {
        this.logger.error(e.getMessage(), e);
      }
    }

    return null;

  }

}
