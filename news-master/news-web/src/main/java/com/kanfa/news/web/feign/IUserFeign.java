package com.kanfa.news.web.feign;

import com.kanfa.news.web.feign.callBack.UserFallBack;
import com.kanfa.news.web.vo.channel.UserAuthInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kanfa on 2018/3/21.
 */

@FeignClient(name = "service-provider-user", fallback = UserFallBack.class)
public interface IUserFeign {


    /**
     * 根据用户手机查找用户
     * @param number
     * @return
     */
    @RequestMapping(value = "/login/selectUserByPhone", method = RequestMethod.POST)
    UserAuthInfo selectUserByPhone(@RequestParam("isRemeber") Boolean isRemeber,
                                   @RequestParam("number") String number,
                                   @RequestParam("cookieId") String cookieId);



    /**
     * redis中清除用户
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/login/removeRedisUser", method = RequestMethod.POST)
    void removeRedisUser(@RequestParam("sessionId") String sessionId);

    /**
     * redis中查找用户
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/login/findRedisUser", method = RequestMethod.POST)
    UserAuthInfo findRedisUser(@RequestParam("sessionId") String sessionId);

}
