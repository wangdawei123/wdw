package com.kanfa.news.web.rpc.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.constant.web.WebCommonParams;
import com.kanfa.news.common.util.GetUserInfoUtil;
import com.kanfa.news.web.feign.IUserAuthFeign;
import com.kanfa.news.web.util.WeChatUtils;
import com.kanfa.news.web.vo.channel.UserAuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * Created by kanfa on 2018/6/15.
 */

@Controller
@RequestMapping("web/user")
public class UserRest {
    @Autowired
    private IUserAuthFeign userAuthFeign;
    @Value("${pc.weixin.auth_domain}")
    private String authDomain;
    @Value("${pc.weixin.app_proxyauth}")
    private String app_proxyauth;
    /**
     * 微信登陆的id
     */
    @Value("${pc.weixin-auth.client_id}")
    private String app_id;

    /**
     * 微信登陆的密钥
     */
    @Value("${pc.weixin-auth.client_secret}")
    private String app_secret;

    /**
     * 微信登陆的回调路径
     */
    @Value("${pc.weixin-auth.redirect_uri}")
    private String redirect_uri;
    /**
     * achieve code ,the first step of Wechat authorization
     */
    @Value("${pc.weixin-auth.achieve_code_uri}")
    private String achieve_code_uri;

    @RequestMapping(value = "/wechatAuth", method = RequestMethod.GET)
    public void wechatAuth(@RequestParam(value = "id" ,required = false) Integer id ,
                                          HttpServletRequest request,HttpServletResponse response,
                                          @RequestParam(value = "code",required = false) String code,
                                          @RequestParam(value = "state",required = false) String state) {
       String stat = id+"" ;
       /*  String type = "live";
        if(null != id){
            stat = id+"";
        }
        String remoteHost = request.getRemoteHost();
        int i = remoteHost.indexOf('.');
        String domain = remoteHost.substring(0 ,i);
        if(!remoteHost.equals(authDomain)){
            stat = domain + "D" + type + "D" + id;
        }*/
       try{
           redirect_uri =  URLEncoder.encode(redirect_uri, "GBK");
       }catch (Exception e){
           e.printStackTrace();
       }
        if(code == null){
            String param = "appid="+app_id
                    +"&redirect_uri="+redirect_uri
                    +"&response_type=code"
                    //非静默授权的方式 ，必须要用户同意授权才能获取用户信息
                    +"&scope=snsapi_userinfo"
                    +"&state=" + stat
                    +"#wechat_redirect";
            try{
                response.sendRedirect(achieve_code_uri + "?" + param);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            //The second step : invoking the method of the WeChatUtils
            String s = WeChatUtils.achieveToken(app_id ,app_secret  ,code);
            //
            JSONObject jsonObject = JSON.parseObject(s);
            String access_token = jsonObject.getString("access_token");
            String openid = jsonObject.getString("openid");
            //The third step: inchieve the user's detail
            String weiXinUserInfo = GetUserInfoUtil.getWeiXinUserInfo(openid, access_token);
            if(weiXinUserInfo != null){
                JSONObject userInfoJO = JSON.parseObject(weiXinUserInfo);
                UserAuthInfo user = new UserAuthInfo();
                user.setOpenid(userInfoJO.getString("openid"));
                user.setNickname(userInfoJO.getString("nickname"));
                if(LiveCommonEnum.GENDER_MAN.equals(userInfoJO.getString("sex"))){
                    user.setGender("m");
                }
                if(LiveCommonEnum.GENDER_WONAN.equals(userInfoJO.getString("sex"))){
                    user.setGender("f");
                }
                user.setImage(userInfoJO.getString("headimgurl"));
                user.setUnionid(userInfoJO.getString("unionid"));

                //getRequestedSessionId() 获取的是客户端浏览器中的jsessionid的值
                String sessionid = "";
                Cookie[] cookies = request.getCookies();
                if (null != cookies) {
                    for(Cookie cookie : cookies){
                        if(WebCommonParams.id.equals(cookie.getName())){
                            sessionid = cookie.getValue();
                        }
                    }
                }
                user.setPlatform(2);
                UserAuthInfo userAuthInfo = userAuthFeign.wechatLogin(user);
                Cookie cookie = null;
                if(userAuthInfo != null){
                    cookie = new Cookie(WebCommonParams.id,sessionid);
                    cookie.setPath("/");
                    cookie.setMaxAge((int)(LiveCommonEnum.WEB_SESSIONID_TIME + System.currentTimeMillis()));
                }else{
                    //找不到用户信息,异常删除用户
                    cookie = new Cookie(WebCommonParams.id,"nouser");
                    cookie.setPath("/");
                    cookie.setMaxAge((int)(300 + System.currentTimeMillis()));
                }
                response.addCookie(cookie);
                response.setHeader("url" ,LiveCommonEnum.URL_PREFIX);
            }else{
                response.setHeader("url" ,LiveCommonEnum.URL_PREFIX + "&websessionid=noauth");
            }
        }
    }



}
