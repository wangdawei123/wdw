package com.kanfa.news.app.feign;

import com.kanfa.news.app.vo.user.PlatformUser;
import com.kanfa.news.common.msg.AppObjectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Administrator
 */
@FeignClient(name = "service-provider-user")
public interface IUserServiceFeign {
    @RequestMapping(value = "/login/userLogin", method = RequestMethod.POST)
    AppObjectResponse userLogin(@RequestParam("code") String code, @RequestParam("phone") String phone,
                                @RequestParam("sessionId") String sessionId,
                                @RequestParam("channel_num") String channelNum);

    @RequestMapping(value = "/login/platformLogin", method = RequestMethod.POST)
    AppObjectResponse platformLogin(@RequestParam("accessToken") String accessToken,
                                    @RequestParam("openId") String openId,
                                    @RequestParam("platform") Integer platform);

    @RequestMapping(value = "/login/portrait", method = RequestMethod.POST)
    AppObjectResponse portrait(@RequestParam("uid") Integer uid,
                               @RequestParam("imagePath") String imagePath);

    /**
     * app端--用户退出
     * @param sessionId
     */
    @RequestMapping(value = "/login/removeAppUser", method = RequestMethod.POST)
    AppObjectResponse removeAPPUser(@RequestParam("sessionId") String sessionId);

    /**
     * app -- 获取用户信息
     */
    @RequestMapping(value = "/login/getusermessage", method = RequestMethod.GET)
    AppObjectResponse getusermessage(@RequestParam("uid") Integer uid);


    @RequestMapping(value = "/login/personal", method = RequestMethod.POST)
    AppObjectResponse personal(@RequestParam("uid") Integer uid,
                               @RequestParam("sessionid") String sessionid);

    /**
     * app -- 我的问答
     */
    @RequestMapping(value = "/login/myProblem", method = RequestMethod.POST)
    AppObjectResponse myProblem(@RequestParam("page") Integer page,
                                @RequestParam("pcount") Integer pcount,
                                @RequestParam("uid") Integer uid);

    /**
     * app -- 提交意见反馈
     */
    @RequestMapping(value = "/feedback/feedbackAdd", method = RequestMethod.POST)
    AppObjectResponse feedbackAdd(@RequestParam("advise") String advise,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("uid") Integer uid);

    /**
     * app -- 控制IOS直播显示与否 -- 我的->律师来了
     */
    @RequestMapping(value = "/login/live", method = RequestMethod.POST)
    AppObjectResponse live();


}
