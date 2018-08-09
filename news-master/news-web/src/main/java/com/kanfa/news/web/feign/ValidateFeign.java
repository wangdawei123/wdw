package com.kanfa.news.web.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.feign.callBack.ValidateFallBack;
import com.kanfa.news.web.vo.channel.UserAuthInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kanfa on 2018/3/22.
 */

@FeignClient(name = "service-provider-common", fallback = ValidateFallBack.class)
public interface ValidateFeign {

    /**
     * 获取一个四位数的验证码
     * @Param phone 手机号
     *
     */
    @RequestMapping(value = "/message/sendValidateCode", method = RequestMethod.POST)
    ObjectRestResponse sendPhoneCode(@RequestParam("phone") String phone);

    /**
     * 验证手机验证码
     * @Param code 手机验证码
     * @Param sessionid
     */
    @RequestMapping(value = "/message/checkSMSCode", method = RequestMethod.POST)
    ObjectRestResponse checkPhoneCode(@RequestParam("code") String code,@RequestParam("sessionid") String sessionId);
}
