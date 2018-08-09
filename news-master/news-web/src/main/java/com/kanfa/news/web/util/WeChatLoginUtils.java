package com.kanfa.news.web.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by kanfa on 2018/6/25.
 * 注：这里应用于微信网页登陆
 */
public class WeChatLoginUtils {

    /**
     * 微信登陆的id
     */
    @Value("${pc.weixin.client_id}")
    private static String app_id;

    /**
     * 微信登陆的密钥
     */
    @Value("${pc.weixin.client_secret}")
    private static String app_secret;

    /**
     * achieve code ,the first step of Wechat authorization
     */
    @Value("${pc.weixin.achieve_code_uri}")
    private static String achieve_code_uri;

    /**
      *  The first step : achieve the code
      *  For example: https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
      */
    public static void achieveCode(String appid ,String redirectUrl ,String stat){
        String path = achieve_code_uri;
        String param = "appid="+app_id
                        +"&redirect_uri="+redirectUrl
                        +"&response_type=code"
                        +"&scope=snsapi_login"
                        +"&state=" + stat;

        //send out the http get request
        com.kanfa.news.common.util.HttpSendUtil.httpGet(path,param);
    }

    /**
     * The second step: achieve the access_token with code
     * For example: https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     */
    public static String achieveToken(String code){
        String path = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String param =   "appid="+app_id
                        +"&secret="+app_secret
                        +"&code="+code
                        +"&grant_type=authorization_code";
        //发送get请求
        String s = com.kanfa.news.common.util.HttpSendUtil.httpGet(path,param);

        return s;
    }

    /**
     * The third step :achieve user's detail
     */



}
