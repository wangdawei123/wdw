package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ActivityVotePeople;
import com.kanfa.news.app.vo.admin.activity.ActivityVotePeoplePageInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 活动--投票--投票人信息表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-12 11:24:51
 */
public interface ActivityVotePeopleMapper extends Mapper<ActivityVotePeople> {


    /**
     * 新增建言/投票活动中的候选人
     * @param
     * @return
     */
    List<ActivityVotePeoplePageInfo> getActivityVotePeoplePage(ActivityVotePeoplePageInfo entity);


    /**
     *建言/投票活动中的候选人 新增
     * @param
     * @return
     */
    int ActivityVotePeopleAdd(ActivityVotePeople entity);

}
