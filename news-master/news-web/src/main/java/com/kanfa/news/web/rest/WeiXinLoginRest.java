package com.kanfa.news.web.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.util.GetUserInfoUtil;
import com.kanfa.news.web.feign.IUserAuthFeign;
import com.kanfa.news.web.feign.IUserFeign;
import com.kanfa.news.web.util.WeChatUtils;
import com.kanfa.news.web.vo.channel.UserAuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by kanfa on 2018/3/19.
 */
@Controller
public class WeiXinLoginRest {

    @Autowired
    private IUserAuthFeign userAuthFeign;

    @Autowired
    private IUserFeign userFeign;

    /**
     * 微信登陆的id
     */
    @Value("${pc.weixin.client_id}")
    private String client_id;

    /**
     * 微信登陆的密钥
     */
    @Value("${pc.weixin.client_secret}")
    private String client_secret;

    /**
     * 微信登陆的回调路径
     */
    @Value("${pc.weixin.redirect_uri}")
    private String redirect_uri;


    /**
     * 微信登陸回調地址
     * 获取access_token、openid
     * 第二步：通过code获取access_token
     * @param code
     * @param state
     * @throws IOException
     */
    @RequestMapping("/weixinCallBack")
    public String weixinCallBack(@RequestParam(value = "code" ,required = false) String code,
                                 @RequestParam(value = "state" ,required = false) String state,
                                 HttpSession session){
        if(code != null){
            //invoking the method of the WeChatUtils
            String s = WeChatUtils.achieveToken(client_id ,client_secret  ,code);
            //
            JSONObject jsonObject = JSON.parseObject(s);
            String access_token = jsonObject.getString("access_token");
            String openid = jsonObject.getString("openid");

            //通过openid和token查找数据库，判断是否第一次登陆
            UserAuthInfo info = userAuthFeign.selecetByWechatOpenId(openid ,access_token);
            if(info == null){
                //如果数据找不到数据，说明该用户是第一次登陆，把用户信息保存到数据库
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
                    user.setPlatform(2);
                    info = userAuthFeign.wechatLogin(user);
                }
                session.setAttribute("userAuthInfo",info);
            }
            session.setAttribute("userAuthInfo",info);
        }
        return "redirect:/";
    }





}

