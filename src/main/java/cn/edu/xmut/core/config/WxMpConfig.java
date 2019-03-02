package cn.edu.xmut.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Binary Wang
 *
 */
@Configuration
public class WxMpConfig {

  private String token = Global.getConfig("wx.token");


  private String appid = Global.getConfig("wx.appid");


  private String appsecret = Global.getConfig("wx.appsecret");


  private String aesKey= Global.getConfig("wx.aesKey");

  public String getToken() {
    return this.token;
  }

  public String getAppid() {
    return this.appid;
  }

  public String getAppsecret() {
    return this.appsecret;
  }

  public String getAesKey() {
    return this.aesKey;
  }

}
