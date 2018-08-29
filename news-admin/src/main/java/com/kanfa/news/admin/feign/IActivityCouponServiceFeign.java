package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.ActivityCoupon;
import com.kanfa.news.admin.vo.activity.ActivityCouponInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/4/23 14:03
 */
@FeignClient(name = "service-provider-news")
public interface IActivityCouponServiceFeign {

    /**
     * 新增 优惠券
     * @param
     * @return
     */
    @RequestMapping(value = "/activityCoupon/insertActivityCoupon",method = RequestMethod.POST)
    ObjectRestResponse insertActivityCoupon(@RequestBody ActivityCouponInfo entity);


    /**
     * 得到详情 优惠券
     * @param
     * @return
     */
    @RequestMapping(value = "/activityCoupon/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<ActivityCoupon> get(@PathVariable("id") int id);

    /**
     * 编辑 优惠券
     * @param
     * @return
     */
    @RequestMapping(value = "/activityCoupon/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<ActivityCoupon> update(@PathVariable("id") Integer id, @RequestBody ActivityCoupon entity);


    /**
     * 优惠券 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/activityCoupon/getActivityCouponPage", method = RequestMethod.POST)
    TableResultResponse<ActivityCouponInfo> getActivityCouponPage(@RequestBody ActivityCouponInfo entity);

}
