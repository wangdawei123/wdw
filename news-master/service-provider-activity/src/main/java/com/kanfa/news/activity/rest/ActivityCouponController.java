package com.kanfa.news.activity.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.activity.biz.ActivityCouponBiz;
import com.kanfa.news.activity.entity.ActivityCoupon;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activityCoupon")
public class ActivityCouponController extends BaseController<ActivityCouponBiz,ActivityCoupon> {

}