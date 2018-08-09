package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ActivityCoupon;
import com.kanfa.news.app.vo.admin.activity.ActivityCouponInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 活动--优惠券表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-23 11:30:16
 */
public interface ActivityCouponMapper extends Mapper<ActivityCoupon> {

    List<ActivityCouponInfo> getActivityCouponPage(ActivityCouponInfo entity);
	
}
