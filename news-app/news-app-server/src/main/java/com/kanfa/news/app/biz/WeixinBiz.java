package com.kanfa.news.app.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kanfa.news.app.vo.admin.weixin.Weixin;
import com.kanfa.news.common.util.HttpSendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author Jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-05-09 19:44:49
 */
@Service
public class WeixinBiz  {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private Weixin weixin;
    public void getSignPackage() {
        StringBuilder param = new StringBuilder();
        param.append("grant_type=").append(weixin.getGrantType())
             .append("&appid=").append(weixin.getAppId())
             .append("&secret=").append(weixin.getAppSecret());
        String tokenString = HttpSendUtil.httpGet(weixin.getTokenUri(),param.toString());
        JSONObject tokenJson = JSON.parseObject(tokenString);
        String access_token = tokenJson.getString("access_token");
         param = new StringBuilder();
         param.append("access_token=").append(access_token).append("&type=jsapi");
        String jsapiTicket = HttpSendUtil.httpGet(weixin.getTiketUri(),param.toString());
        JSONObject ticketJson = JSON.parseObject(jsapiTicket);
        redisTemplate.opsForValue().set("weixin_accesstoken",tokenJson.toJSONString());
        redisTemplate.opsForValue().set("weixin_jsapiticket",ticketJson.toJSONString());
    }
}