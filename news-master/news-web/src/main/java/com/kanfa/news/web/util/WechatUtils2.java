package com.kanfa.news.web.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kanfa on 2018/7/5.
 */
public class WechatUtils2 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("https://watchlive.dongweinet.com/login/wx", "UTF-8");
        getWeChartCode("wx592fd002d6a0bacd", encode, "snsapi_login");
    }


    /**
     * 用户同意授权，获取code
     *
     * @param app_id
     * @param redirectUri
     * https://open.weixin.qq.com/connect/qrconnect?appid=wx373a1721b3336d00&redirect_uri=https%3A%2F%2Fwatchlive.dongweinet.com%2Flogin%2Fwx&response_type=code&scope=snsapi_login&state=123#wechat_redirect
     * @param snsapiBase
     * @return
     */
    public static Map<String, String> getWeChartCode(String app_id, String redirectUri, String snsapiBase) {
        JsonObject object = null;
        Map<String, String> data = new HashMap();
        try {
            String url = String.format("https://open.weixin.qq.com/connect/qrconnect?"
                            + "appid=%s"
                            + "&redirect_uri=%s"
                            + "&response_type=code"
                            + "&scope=%s"
                            + "&state=123"
                            + "#wechat_redirect",
                    app_id, redirectUri,snsapiBase);
            System.out.println("request accessToken from url: {}"+ url);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String tokens = EntityUtils.toString(httpEntity, "utf-8");
            Gson token_gson = new Gson();
            object = token_gson.fromJson(tokens, JsonObject.class);
            System.out.println("request accessToken success. [result={}]"+ object);
            data.put("openid", object.get("openid").toString().replaceAll("\"", ""));
            data.put("access_token", object.get("access_token").toString().replaceAll("\"", ""));
        } catch (Exception ex) {
            System.out.println("fail to request wechat access token. [error={}]"+ ex);
        }
        return data;
    }






    /**
     * 获取请求用户信息的access_token
     *
     * @param code
     * @return
     */
    public static Map<String, String> getUserInfoAccessToken(String code,String app_id,String app_secret) {
        JsonObject object = null;
        Map<String, String> data = new HashMap();
        try {
            String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                    app_id, app_secret, code);
            System.out.println("request accessToken from url: {}"+ url);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String tokens = EntityUtils.toString(httpEntity, "utf-8");
            Gson token_gson = new Gson();
            object = token_gson.fromJson(tokens, JsonObject.class);
            System.out.println("request accessToken success. [result={}]"+ object);
            data.put("openid", object.get("openid").toString().replaceAll("\"", ""));
            data.put("access_token", object.get("access_token").toString().replaceAll("\"", ""));
        } catch (Exception ex) {
            System.out.println("fail to request wechat access token. [error={}]"+ ex);
        }
        return data;
    }


    /**
     * 获取用户信息
     *
     * @param accessToken
     * @param openId
     * @return
     */
    public static Map<String, String> getUserInfo(String accessToken, String openId) {
        Map<String, String> data = new HashMap();
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
        System.out.println("request user info from url: {"+url+"}");
        JsonObject userInfo = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(httpEntity, "utf-8");
            Gson token_gson = new Gson();
            userInfo = token_gson.fromJson(response, JsonObject.class);
            System.out.println("get userinfo success. [result={"+userInfo+"}]");
            data.put("openid", userInfo.get("openid").toString().replaceAll("\"", ""));
            data.put("nickname", userInfo.get("nickname").toString().replaceAll("\"", ""));
            data.put("city", userInfo.get("city").toString().replaceAll("\"", ""));
            data.put("province", userInfo.get("province").toString().replaceAll("\"", ""));
            data.put("country", userInfo.get("country").toString().replaceAll("\"", ""));
            data.put("headimgurl", userInfo.get("headimgurl").toString().replaceAll("\"", ""));
        } catch (Exception ex) {
            System.out.println("fail to request wechat user info. [error={"+ex+"}]");
        }
        return data;
    }


}
