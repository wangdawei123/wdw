package com.kanfa.news.web.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kanfa.news.common.constant.web.WebCommonParams;
import com.kanfa.news.common.util.HttpSendUtil;
import com.kanfa.news.web.feign.IUserAuthFeign;
import com.kanfa.news.web.feign.IUserFeign;
import com.kanfa.news.web.vo.channel.UserAuthInfo;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanfa on 2018/3/22.
 */

@Controller
public class QQLoginRest {
    @Autowired
    private IUserAuthFeign userAuthFeign;

    @Autowired
    private IUserFeign userFeign;

    /**
     * qq登陆的id
     */
    @Value("${pc.qq.client_id}")
    private String qclient_id;

    /**
     * qq登陆的密钥
     */
    @Value("${pc.qq.client_secret}")
    private String qclient_secret;

    /**
     * qq登陆的回调路径
     */
    @Value("${pc.qq.redirect_uri}")
    private String qredirect_uri;
    /**
     * qq登陆-发送code获取token-第2步
     */
    private final String SENDCODEPATH = "https://graph.qq.com/oauth2.0/token";
    /**
     * qq登陆-发送token获取openid-第3步
     */
    private final String SENDTOKENPATH = "https://graph.qq.com/oauth2.0/me";


    /**
     * QQ第三方登陆的回调路径
     * @param code
     * @param state
     */
    @RequestMapping("/mycb")
    public String qqLoad(String code, String state,  HttpSession session){
        //通过code获取token
        String pathToken = SENDCODEPATH;
        String paramToken = "grant_type=authorization_code&client_id="+qclient_id
                            +"&client_secret=" + qclient_secret
                            + "&code=" + code
                            + "&redirect_uri=" + qredirect_uri;
        //发送get请求
        String tokenString = HttpSendUtil.httpGet(pathToken, paramToken);
        //处理返回的字条串access_token=B99E505A943AF9E39C938B5974775B9F&expires_in=7776000&refresh_token=707EACB53A328235FD5D4FB9658BA57E
        String[] split = tokenString.split("&");
        String accessToken = split[0].substring(13);

        //通过token获取openId
        String pathOpenId = SENDTOKENPATH;
        String paramOpenId = "access_token="+accessToken;
        //发送get请求
        String openIdStr = HttpSendUtil.httpGet(pathOpenId, paramOpenId);
        //处理返回的数据callback( {"client_id":"101463083","openid":"19FE8F8BD136C58F5CBBC1C06B3D186C"} )
        String[] srr = openIdStr.split("\"");
        String openId = srr[7];

        //通过openId查找数据库，判断是否第一次登陆
        UserAuthInfo info = userAuthFeign.selecetByQQOpenId(openId ,accessToken ,qclient_id);
        session.setAttribute("userAuthInfo",info);
        return "redirect:/";
    }

    @Test
    public void qqtest(){
        //通过code获取token
        String pathToken = "https://graph.qq.com/oauth2.0/token";
        String paramToken = "grant_type=authorization_code" +
                "&client_id=101463083&client_secret=be0211340f8c65cfdbfa8f70813e1387" +
                "&code=04CEC6027832E46E2749D623E33448BD&redirect_uri=http://java-web.vrcdkj.cn/mycb";
        //发送get请求
        String tokenString = HttpSendUtil.httpGet(pathToken, paramToken);
        //处理返回的字条串access_token=B99E505A943AF9E39C938B5974775B9F&expires_in=7776000&refresh_token=707EACB53A328235FD5D4FB9658BA57E
        String[] split = tokenString.split("&");
        String accessToken = split[0].substring(13);

        //通过token获取openId
        String pathOpenId = "https://graph.qq.com/oauth2.0/me";
        String paramOpenId = "access_token="+accessToken;
        //发送get请求
        String openIdStr = HttpSendUtil.httpGet(pathOpenId, paramOpenId);
        //处理返回的数据callback( {"client_id":"101463083","openid":"19FE8F8BD136C58F5CBBC1C06B3D186C"} )
        String[] srr = openIdStr.split("\"");
        String openId = srr[7];
    }


    @Test
    public void weibo(){
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("client_id", "1944164462"));
        nvps.add(new BasicNameValuePair("client_secret", "5fbcbb29da424569252a16437257abb5"));
        nvps.add(new BasicNameValuePair("grant_type", WebCommonParams.granyt_type));
        nvps.add(new BasicNameValuePair("redirect_uri", "http://java-web.vrcdkj.cn/"));
        nvps.add(new BasicNameValuePair("code", "fad07a438965251a95da7bf139a90602"));

        //2.通过code，请求得到token
        String result = HttpSendUtil.sentPost(nvps, "https://api.weibo.com/oauth2/access_token");
        //解析json数据
        JSONObject jsonObject = JSON.parseObject(result);
        String access_token = jsonObject.getString("access_token");
        String uid = jsonObject.getString("uid");
    }


}
