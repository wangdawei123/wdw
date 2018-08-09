package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.entity.UserActivityCoupon;
import com.kanfa.news.info.mapper.UserActivityCouponMapper;
import com.kanfa.news.info.vo.admin.activity.ActivityCouponInfo;
import com.kanfa.news.info.vo.admin.activity.UserActivityCouponInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 用户领取的优惠券表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-23 17:09:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserActivityCouponBiz extends BaseBiz<UserActivityCouponMapper,UserActivityCoupon> {

    @Autowired
    private UserActivityCouponMapper userActivityCouponMapper;

    public TableResultResponse<UserActivityCouponInfo> getUserActivityCouponpage(UserActivityCouponInfo entity){
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //未删除 已领取
        entity.setIsDelete(0);
        entity.setCouponStatus(0);
        List<UserActivityCouponInfo> list = userActivityCouponMapper.getUserActivityCouponpage(entity);
        return new TableResultResponse<UserActivityCouponInfo>(result.getTotal(),list);
    }

    public List<UserActivityCouponInfo> getList(UserActivityCouponInfo entity){
        entity.setIsDelete(0);
        entity.setCouponStatus(0);
        return userActivityCouponMapper.getUserActivityCouponpage(entity);
    }
}