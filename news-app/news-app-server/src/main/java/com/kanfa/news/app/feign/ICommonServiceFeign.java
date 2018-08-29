package com.kanfa.news.app.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-provider-common")
public interface ICommonServiceFeign {

    @RequestMapping(value = "/message/sendValidateCode",method = RequestMethod.POST)
    ObjectRestResponse sendValidateCode(@RequestParam("phone") String phone);
}
