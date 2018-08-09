package com.kanfa.news.web.feign;

import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-provider-activity")
public interface IActivityServiceFeign {


    /**
     *  青春微普法活动  ---- 暂停，后延
     */
    @RequestMapping(value = "/userActivityCoupon/microvideoIndex", method = RequestMethod.POST)
    ObjectRestResponse microvideoIndex(@RequestParam("activity_id") Integer activity_id,
                                       @RequestParam("is_weixin") Integer is_weixin);

}
