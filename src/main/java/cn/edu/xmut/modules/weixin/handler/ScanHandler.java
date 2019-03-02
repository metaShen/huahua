package cn.edu.xmut.modules.weixin.handler;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Component;


import cn.edu.xmut.modules.weixin.WeixinService;
import cn.edu.xmut.modules.weixin.builder.TextBuilder;
import cn.edu.xmut.utils.Constants;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 
 * @author Binary Wang
 *
 */
@Component
public class ScanHandler extends AbstractHandler {
	@Resource(name = "WeixinService")
	private WeixinService wxService;
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {

		return null;
	}

}
