package com.kanfa.news.app.feign;

import com.kanfa.news.common.msg.AppObjectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-provider-activity")
public interface IActivityServiceFeign {


    /**
     *  青春微普法活动--首页接口
     */
    @RequestMapping(value = "/activityAward/getLawList", method = RequestMethod.POST)
    AppObjectResponse getLawList(@RequestParam("page") Integer page,
                                 @RequestParam("pcount") Integer pcount);

    /**
     *  app - 抽奖消息列表接口
     */
    @RequestMapping(value = "/activityAward/getMessage", method = RequestMethod.POST)
    AppObjectResponse getMessage(@RequestParam(value = "uid") Integer uid);

    /**
     *  app - 活动--优惠券列表页 没过期的
     */
    @RequestMapping(value = "/activityAward/appGetUserCouponAvailableList", method = RequestMethod.POST)
    AppObjectResponse appGetUserCouponAvailableList(@RequestParam(value = "uid") Integer uid,
                                                    @RequestParam(value = "page") Integer page,
                                                    @RequestParam(value = "pcount") Integer pcount);

    /**
     *  app - 活动--优惠券列表页 已过期的
     */
    @RequestMapping(value = "/activityAward/appGetUserCouponList", method = RequestMethod.POST)
    AppObjectResponse appGetUserCouponList(@RequestParam(value = "uid") Integer uid,
                                           @RequestParam(value = "page") Integer page,
                                           @RequestParam(value = "pcount") Integer pcount);


}
