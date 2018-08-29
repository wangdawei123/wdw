package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ActivityCouponBiz;
import com.kanfa.news.info.entity.ActivityCoupon;
import com.kanfa.news.info.vo.admin.activity.ActivityCouponInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activityCoupon")
public class ActivityCouponController extends BaseController<ActivityCouponBiz,ActivityCoupon> {

    @Autowired
    private ActivityCouponBiz activityCouponBiz;

    /**
     * 新增 优惠券
     * @param
     * @return
     */
    @RequestMapping(value = "/insertActivityCoupon",method = RequestMethod.POST)
    public void insertActivityCoupon(@RequestBody ActivityCouponInfo entity){
        activityCouponBiz.insertActivityCoupon(entity);
    }

    /**
     * 优惠券 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getActivityCouponPage", method = RequestMethod.POST)
    public TableResultResponse<ActivityCouponInfo> getActivityCouponPage(@RequestBody ActivityCouponInfo entity) {
        return activityCouponBiz.getActivityCouponPage(entity);
    }



}