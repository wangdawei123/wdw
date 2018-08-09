package com.kanfa.news.activity.mapper;

import com.kanfa.news.activity.entity.UserActivityCoupon;
import com.kanfa.news.activity.vo.info.UserActivityCouponInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户领取的优惠券表
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-23 11:33:20
 */
public interface UserActivityCouponMapper extends Mapper<UserActivityCoupon> {

    List<UserActivityCouponInfo> appGetUserCouponAvailableLis(@Param("uid") Integer uid , @Param("offset") Integer offset , @Param("pcount") Integer pcount);

    List<UserActivityCouponInfo> appGetUserCouponList(@Param("uid") Integer uid , @Param("offset") Integer offset , @Param("pcount") Integer pcount);

}
