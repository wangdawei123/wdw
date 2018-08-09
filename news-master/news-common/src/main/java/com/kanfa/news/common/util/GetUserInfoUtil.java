package com.kanfa.news.common.util;

/**
 * 通过token向微博发送请求，以得到用户详细信息
 * Created by kanfa on 2018/3/24.
 */

public class GetUserInfoUtil {

    /**
     * 通过token请求得到微博用户详细信息
     */
    public static String getWeiBoUserInfo(String uid , String access_token) {
        String getUserPath = "https://api.weibo.com/2/users/show.json";
        String param = "access_token=" + access_token + "&uid=" + uid;
        String s = HttpSendUtil.httpGet(getUserPath, param);
        return s;
    }


    /**
     * 通过token请求得到QQ用户详细信息
     */
    public static String getQQUserInfo(String client_id , String openId , String access_token) {
        String path_info = "https://graph.qq.com/user/get_user_info";
        String param_info = "oauth_consumer_key=" + client_id + "&access_token=" + access_token + "&openid=" + openId ;
        String s = HttpSendUtil.httpGet(path_info, param_info);
        return s;
    }


    /**
     * 通过token请求得到微信用户详细信息
     */
    public static String getWeiXinUserInfo(String openId , String access_token) {
        String path_info = "https://api.weixin.qq.com/sns/userinfo";
        String param_info = "access_token=" + access_token + "&openid=" + openId + "&lang=zh_CN";
        String s = HttpSendUtil.httpGet(path_info, param_info);
        return s;
    }
}
