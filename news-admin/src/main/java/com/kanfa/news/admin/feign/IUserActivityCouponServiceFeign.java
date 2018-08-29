package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.vo.activity.UserActivityCouponInfo;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Chen
 * on 2018/4/23 17:55
 */

@FeignClient(name = "service-provider-news")
public interface IUserActivityCouponServiceFeign {

    @RequestMapping(value = "/userActivityCoupon/getUserActivityCouponpage",method = RequestMethod.POST)
    TableResultResponse<UserActivityCouponInfo> getUserActivityCouponpage(@RequestBody UserActivityCouponInfo entity);


    @RequestMapping(value = "/userActivityCoupon/excel",method = RequestMethod.POST)
    ExcelData getUserActivityCouponExcel(@RequestBody UserActivityCouponInfo entity);

    @RequestMapping(value = "/userActivityCoupon/excel1",method = RequestMethod.POST)
    ExcelData getActivityCouponExcel(@RequestBody UserActivityCouponInfo entity);
}
