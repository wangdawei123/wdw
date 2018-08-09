package com.kanfa.news.quartz.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by Chen
 * on 2018/4/23 14:03
 */
@FeignClient(name = "service-provider-news")
public interface IActivityCouponServiceFeign {

}
