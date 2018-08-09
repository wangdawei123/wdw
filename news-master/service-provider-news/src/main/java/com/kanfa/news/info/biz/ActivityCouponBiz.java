package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.entity.ActivityCoupon;
import com.kanfa.news.info.mapper.ActivityCouponMapper;
import com.kanfa.news.info.vo.admin.activity.ActivityCouponInfo;
import com.kanfa.news.info.vo.admin.activity.PioneerActivityPageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 活动--优惠券表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-23 11:30:16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityCouponBiz extends BaseBiz<ActivityCouponMapper,ActivityCoupon> {

    @Autowired
    private ActivityCouponMapper activityCouponMapper;

    /**
     * 新增 优惠券
     * @param
     * @return
     */
    public void insertActivityCoupon(ActivityCouponInfo entity){
        List<String> codes = entity.getCodes();
        if (codes.size() > 0 && codes != null){
            for (String code: codes) {
                ActivityCoupon activityCoupon = new ActivityCoupon();
                BeanUtils.copyProperties(entity,activityCoupon);
                activityCoupon.setCode(code);
                activityCouponMapper.insertSelective(activityCoupon);
            }
        }
    }

    /**
     * 优惠券 分页及搜索
     * @param
     * @return
     */
    public TableResultResponse<ActivityCouponInfo> getActivityCouponPage(ActivityCouponInfo entity){
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        entity.setIsDelete(0);
        List<ActivityCouponInfo> list = activityCouponMapper.getActivityCouponPage(entity);
        return new TableResultResponse<ActivityCouponInfo>(result.getTotal(),list);
    }


}

