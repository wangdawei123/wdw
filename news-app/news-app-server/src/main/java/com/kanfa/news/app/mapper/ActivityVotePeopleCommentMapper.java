package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ActivityVotePeopleComment;
import com.kanfa.news.app.vo.admin.activity.ActivityVotePeopleCommentPageInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 活动--投票--投票人评论信息表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-12 16:01:30
 */
public interface ActivityVotePeopleCommentMapper extends Mapper<ActivityVotePeopleComment> {

    /**
     * 候选人评论列表 的分页及按审核状态搜索
     * @param
     * @return
     */
    List<ActivityVotePeopleCommentPageInfo> getActivityVotePeopleCommentPage(ActivityVotePeopleCommentPageInfo entity);
}
