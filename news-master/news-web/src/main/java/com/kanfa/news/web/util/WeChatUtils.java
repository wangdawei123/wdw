package com.kanfa.news.web.util;

/**
 * Created by kanfa on 2018/6/25.
 * 注：这里应用于微信公众号授权，和微信网页登陆参数有所不同
 */
public class WeChatUtils {

    /**
      *  The first step : achieve the code
      *  For example: https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
      */
    public static void achieveCode(String appid ,String redirectUrl ,String stat ,String achieve_code_uri){
        String path = achieve_code_uri;
        String param = "appid="+appid
                        +"&redirect_uri="+redirectUrl
                        +"&response_type=code"
                        //非静默授权的方式 ，必须要用户同意授权才能获取用户信息
                        +"&scope=snsapi_userinfo"
                        +"&state=" + stat
                        +"#wechat_redirect";

        //send out the http get request
        com.kanfa.news.common.util.HttpSendUtil.httpGet(path,param);
    }

    /**
     * The second step: achieve the access_token with code
     * For example: https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     */
    public static String achieveToken(String appid ,String app_secret ,String code){
        String path = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String param =   "appid="+appid
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
