package com.kanfa.news.web.feign;

import com.kanfa.news.web.feign.callBack.UserAuthFallBack;
import com.kanfa.news.web.vo.channel.UserAuthInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kanfa on 2018/3/21.
 */

@FeignClient(name = "service-provider-user", fallback = UserAuthFallBack.class)
public interface IUserAuthFeign {

    /**
     * 根据用户微博openId查找用户
     * @param uid
     * @return
     */
    @RequestMapping(value = "/binding/selecetByWiboOpenId",method = RequestMethod.POST)
    UserAuthInfo selecetByWiboOpenId(@RequestParam("uid") String uid,
                                     @RequestParam("access_token") String access_token);

    /**
     * 根据用户微信openId查找用户
     * @param openid
     * @return
     */
    @RequestMapping(value = "/binding/selecetByWechatOpenId",method = RequestMethod.POST)
    UserAuthInfo selecetByWechatOpenId( @RequestParam("openid") String openid,
                                        @RequestParam("access_token") String access_token);

    @RequestMapping(value = "/binding/wechatLogin",method = RequestMethod.POST)
    UserAuthInfo wechatLogin( @RequestBody UserAuthInfo user);

    /**
     * 根据用户QQ openId查找用户
     * @return
     */
    @RequestMapping(value = "/binding/selecetByQQOpenId",method = RequestMethod.POST)
    UserAuthInfo selecetByQQOpenId(  @RequestParam("openId") String openId,
                                     @RequestParam("accessToken") String accessToken,
                                     @RequestParam("qclient_id") String qclient_id);


}
