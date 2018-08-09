package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.Activity;
import com.kanfa.news.app.vo.admin.activity.CouponsActivityPageInfo;
import com.kanfa.news.app.vo.admin.activity.PioneerActivityPageInfo;
import com.kanfa.news.app.vo.admin.activity.VoteActivityInfo;
import com.kanfa.news.app.vo.admin.activity.YouthActivityPageInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 活动表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-10 17:01:20
 */
public interface ActivityMapper extends Mapper<Activity> {


    /**
     * 新增建言/投票活动
     * @param
     * @return
     */
    int  addVoteActivity(Activity entity);

    /**
     * 建言/投票活动的分页显示
     * @param
     * @return
     */
    List<VoteActivityInfo> getVoteActivityPage(VoteActivityInfo entity);

    /**
     * 优惠券活动的分页显示
     * @param
     * @return
     */
    List<CouponsActivityPageInfo>  getCouponsActivityPage(CouponsActivityPageInfo entity);


    /**
     * 青春微普法大赛活动的分页显示
     * @param
     * @return
     */
    List<YouthActivityPageInfo> getYouthActivityPage(YouthActivityPageInfo entity);


    /**
     * 政法先锋 分页显示及搜索
     * @param
     * @return
     */
    List<PioneerActivityPageInfo> getPioneerActivityPage(PioneerActivityPageInfo entity);


}
