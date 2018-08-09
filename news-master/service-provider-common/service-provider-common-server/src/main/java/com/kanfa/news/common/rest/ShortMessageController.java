package com.kanfa.news.common.rest;

import com.kanfa.news.common.biz.ShortMessageBiz;
import com.kanfa.news.common.constant.UserEnum;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("message")
public class ShortMessageController {
    @Autowired
    private ShortMessageBiz shortMessageBiz;

    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "/sendValidateCode", method = RequestMethod.POST)
    public ObjectRestResponse sendValidateCode(@RequestParam("phone") String phone) {
        ObjectRestResponse entityObjectRestResponse = new ObjectRestResponse<>();
        Assert.notNull(phone, UserEnum.LoginExceptionEnum.PARAM_VALIDATE_FAIL.value());
        Map<String, String> dataMap = new HashMap<>(1);
        //为测试号码提供一个长期的验证 15910442846-李嘉临  18521527972-金宗丽
        if(("15910442846").equals(phone) || "18521527972".equals(phone)){
            dataMap.put("sessionid", "2335325129681072638");
        }else{
            dataMap.put("sessionid", shortMessageBiz.sendValidateCode(phone));
        }
        entityObjectRestResponse.data(dataMap);
        return entityObjectRestResponse;
    }


    /**
     * 校验短信验证码
     *
     * @param code
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/checkSMSCode", method = RequestMethod.POST)
    public ObjectRestResponse checkSMSCode(@RequestParam("code") String code,
                                           @RequestParam("sessionid") String sessionId) {
        Assert.notNull(code, UserEnum.LoginExceptionEnum.PARAM_VALIDATE_FAIL.value());
        Assert.notNull(sessionId, UserEnum.LoginExceptionEnum.PARAM_VALIDATE_FAIL.value());
        shortMessageBiz.checkSMSCode(code, sessionId);
        return new ObjectRestResponse();
    }
}
