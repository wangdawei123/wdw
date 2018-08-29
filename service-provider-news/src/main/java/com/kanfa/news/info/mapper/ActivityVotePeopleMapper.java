package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.ActivityVotePeople;
import com.kanfa.news.info.vo.admin.activity.ActivityVotePeopleAddInfo;
import com.kanfa.news.info.vo.admin.activity.ActivityVotePeoplePageInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

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
