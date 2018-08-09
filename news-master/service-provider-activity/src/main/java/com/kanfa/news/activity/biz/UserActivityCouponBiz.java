package com.kanfa.news.activity.biz;

import com.kanfa.news.activity.vo.info.UserActivityCouponInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.activity.entity.UserActivityCoupon;
import com.kanfa.news.activity.mapper.UserActivityCouponMapper;
import com.kanfa.news.common.biz.BaseBiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户领取的优惠券表
 *
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-23 11:33:20
 */
@Service
public class UserActivityCouponBiz extends BaseBiz<UserActivityCouponMapper,UserActivityCoupon> {

    @Autowired
    private UserActivityCouponMapper userActivityCouponMapper;


    public List<Map<String, Object>> appGetUserCouponAvailableLis(Integer uid ,Integer page ,Integer pcount){
        List<UserActivityCouponInfo> infos = userActivityCouponMapper.appGetUserCouponAvailableLis(uid, (page - 1) * pcount, pcount);
        List<Map<String, Object>> list = new ArrayList<>();
        for(UserActivityCouponInfo info : infos){
            Map<String ,Object> map = new HashMap<>(12);
            map.put("activity_coupon_start_time" ,info.getActivityCouponStartTime());
            map.put("activity_coupon_end_time" ,info.getActivityCouponEndTime());
            map.put("activity_merchant" ,info.getActivityMerchant());
            map.put("coupon_code_name" ,info.getCouponCodeName());
            map.put("activity_coupon_code" ,info.getActivityCouponCode());
            map.put("id" ,info.getId());
            map.put("activity_coupon_id" ,info.getActivityCouponId());
            map.put("content" ,info.getContent());
            map.put("stat" ,info.getStat());
            list.add(map);
        }
        return list;
    }


    public List<Map<String, Object>> appGetUserCouponList(Integer uid ,Integer page ,Integer pcount){
        List<UserActivityCouponInfo> infos = userActivityCouponMapper.appGetUserCouponList(uid, (page - 1) * pcount, pcount);
        List<Map<String, Object>> list = new ArrayList<>();
        for(UserActivityCouponInfo info : infos){
            Map<String ,Object> map = new HashMap<>(12);
            map.put("activity_coupon_start_time" ,info.getActivityCouponStartTime());
            map.put("activity_coupon_end_time" ,info.getActivityCouponEndTime());
            map.put("activity_merchant" ,info.getActivityMerchant());
            map.put("coupon_code_name" ,info.getCouponCodeName());
            map.put("activity_coupon_code" ,info.getActivityCouponCode());
            map.put("id" ,info.getId());
            map.put("activity_coupon_id" ,info.getActivityCouponId());
            map.put("content" ,info.getContent());
            list.add(map);
        }
        return list;
    }

}