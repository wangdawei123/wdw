package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.UserActivityCoupon;
import com.kanfa.news.app.vo.admin.activity.UserActivityCouponInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 用户领取的优惠券表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-23 17:09:48
 */
public interface UserActivityCouponMapper extends Mapper<UserActivityCoupon> {

    List<UserActivityCouponInfo> getUserActivityCouponpage(UserActivityCouponInfo entity);
	
}
