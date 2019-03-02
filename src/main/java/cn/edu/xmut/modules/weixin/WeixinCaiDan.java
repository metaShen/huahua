package cn.edu.xmut.modules.weixin;
import java.util.ArrayList;
import java.util.List;

import cn.edu.xmut.core.config.WxMpConfig;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

public class WeixinCaiDan{
	public static void main(String[] args)throws WxErrorException {
		WeixinService wxService = new WeixinService();
		WxMpConfig wxMpConfig = new WxMpConfig();
		WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
		configStorage.setAppId(wxMpConfig.getAppid());
		configStorage.setSecret(wxMpConfig.getAppsecret());
		configStorage.setToken(wxMpConfig.getToken());
		configStorage.setAesKey(wxMpConfig.getAesKey());
		wxService.setWxMpConfigStorage(configStorage);
		
		//按钮1--click事件
		WxMenuButton wxMenuButton1 = new WxMenuButton();
		wxMenuButton1.setName("可借数量");
		wxMenuButton1.setKey("MENU_CANLEND");
		wxMenuButton1.setType(WxConsts.MenuButtonType.CLICK);
		
		//按钮2--打开网页
		WxMenuButton wxMenuButton2 = new WxMenuButton();
		wxMenuButton2.setName("进入系统");
		wxMenuButton2.setUrl("http://lkh.frp.yxd.ink/book/login.jhtml");
		wxMenuButton2.setType(WxConsts.MenuButtonType.VIEW);
		
		//按钮3 -- 子按钮1
		WxMenuButton wxMenuButtonSub1 = new WxMenuButton();
		wxMenuButtonSub1.setName("注册账号");
		wxMenuButtonSub1.setUrl("http://lkh.frp.yxd.ink/book/reg.jhtml");
		wxMenuButtonSub1.setType(WxConsts.MenuButtonType.VIEW);
		
		//按钮3 --子按钮2
		WxMenuButton wxMenuButtonSub2 = new WxMenuButton();
		wxMenuButtonSub2.setName("登陆账号");
		wxMenuButtonSub2.setUrl("http://lkh.frp.yxd.ink/book/login.jhtml");
		wxMenuButtonSub2.setType(WxConsts.MenuButtonType.VIEW);
		
		//将两个子按钮放到List<WXMenuButton>里
		List<WxMenuButton> wxMenuButtonSub = new ArrayList<WxMenuButton>();
		wxMenuButtonSub.add(0,wxMenuButtonSub1);
		wxMenuButtonSub.add(1,wxMenuButtonSub2);
		
		//按钮3 --二级菜单
		WxMenuButton wxMenuButton3 = new WxMenuButton();
		wxMenuButton3.setName("登陆/注册");
		wxMenuButton3.setSubButtons(wxMenuButtonSub);
		
		//将3个一级按钮一起放到list里
		List<WxMenuButton> wxMenuButton = new ArrayList<WxMenuButton>();
		wxMenuButton.add(0,wxMenuButton1);
		wxMenuButton.add(1,wxMenuButton2);
		wxMenuButton.add(2,wxMenuButton3);
		
		//设置按钮
		WxMenu wxMenu = new WxMenu();
		wxMenu.setButtons(wxMenuButton);
		wxService.getMenuService().menuCreate(wxMenu);
	}
}