package com.kanfa.news.app.vo.admin.weixin;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/9 11:15
 */
@Component
@ConfigurationProperties(prefix="pc.weixin")
public class Weixin   implements Serializable {
    private static final long serialVersionUID = 1L;
    private String grantType;
    private String appId;
    private String appSecret;
    private String tokenUri;
    private String tiketUri;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getTokenUri() {
        return tokenUri;
    }

    public void setTokenUri(String tokenUri) {
        this.tokenUri = tokenUri ;
    }

    public String getTiketUri() {
        return tiketUri;
    }

    public void setTiketUri(String tiketUri) {
        this.tiketUri = tiketUri;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
