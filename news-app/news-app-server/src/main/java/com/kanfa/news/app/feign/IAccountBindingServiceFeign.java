package com.kanfa.news.app.feign;

import com.kanfa.news.app.vo.user.PlatformUser;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author wdw
 * APP  绑定与解绑 模块
 *
 */
@FeignClient(name = "service-provider-user")
public interface IAccountBindingServiceFeign {
    /**
     * 第三方账号绑定
     * @param uid
     * @param access_token
     * @param openid
     * @param platform
     * @return
     */
    @RequestMapping(value = "/binding/bindSocialAccount", method = RequestMethod.POST)
    ObjectRestResponse bindSocialAccount(@RequestParam("uid") Integer uid,
                                         @RequestParam("access_token") String access_token,
                                         @RequestParam("openid") String openid,
                                         @RequestParam("platform") Integer platform);

    /**
     * 验证待绑定手机号
     * @param
     * @return
     */
    @RequestMapping(value = "/binding/checkPhone", method = RequestMethod.POST)
    ObjectRestResponse checkPhone(@RequestParam("phone") String phone);

    /**
     * 第三方账号解绑并绑定新账户
     * @param uid
     * @param access_token
     * @param openid
     * @param platform
     * @return
     */
    @RequestMapping(value = "/binding/newBindAccount", method = RequestMethod.POST)
    ObjectRestResponse newBindAccount(@RequestParam("uid") Integer uid,
                                      @RequestParam("access_token") String access_token,
                                      @RequestParam("openid") String openid,
                                      @RequestParam("platform") Integer platform);

    /**
     * 手机账号解绑并绑定新账户
     * @param uid
     * @param code
     * @param phone
     * @param verificatcode
     * @return
     */
    @RequestMapping(value = "/binding/newPhoneBinding", method = RequestMethod.POST)
    ObjectRestResponse newPhoneBinding(@RequestParam("uid") Integer uid,
                                       @RequestParam("code") String code,
                                       @RequestParam("phone") String phone,
                                       @RequestParam("verificatcode") String verificatcode);

    /**
     * 第三方登录后绑定手机号
     * @param uid
     * @param code
     * @param phone
     * @param verificatcode
     * @return
     */
    @RequestMapping(value = "/binding/phoneBinding", method = RequestMethod.POST)
    ObjectRestResponse phoneBinding(@RequestParam("uid") Integer uid,
                                    @RequestParam("code") String code,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("verificatcode") String verificatcode);

    /**
     * 第三方账号解绑
     * @param uid
     * @param openid
     * @param platform
     * @return
     */
    @RequestMapping(value = "/binding/unBindSocialAccount", method = RequestMethod.POST)
    ObjectRestResponse unBindSocialAccount(@RequestParam("uid") Integer uid,
                                           @RequestParam("openid") String openid,
                                           @RequestParam("platform") Integer platform);

    /**
     * 修改绑定的手机号
     * @param uid
     * @param code
     * @param oldPhone
     * @param newPhone
     * @param verificatcode
     * @return
     */
    @RequestMapping(value = "/binding/updatePhone", method = RequestMethod.POST)
    ObjectRestResponse updatePhone(@RequestParam("uid") Integer uid,
                                   @RequestParam("code") String code,
                                   @RequestParam("oldPhone") String oldPhone,
                                   @RequestParam("newPhone") String newPhone,
                                   @RequestParam("verificatcode") String verificatcode);




}
