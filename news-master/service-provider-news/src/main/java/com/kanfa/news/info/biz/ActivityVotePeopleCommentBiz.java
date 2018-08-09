package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.vo.admin.activity.ActivityVotePeopleCommentPageInfo;
import com.kanfa.news.info.vo.admin.activity.ActivityVotePeoplePageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.ActivityVotePeopleComment;
import com.kanfa.news.info.mapper.ActivityVotePeopleCommentMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 活动--投票--投票人评论信息表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-12 16:01:30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityVotePeopleCommentBiz extends BaseBiz<ActivityVotePeopleCommentMapper,ActivityVotePeopleComment> {

    @Autowired
    private ActivityVotePeopleCommentMapper activityVotePeopleCommentMapper;

    /**
     * 候选人评论列表 的分页及按审核状态搜索
     * @param
     * @return
     */
    public TableResultResponse<ActivityVotePeopleCommentPageInfo> getActivityVotePeopleCommentPage(ActivityVotePeopleCommentPageInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //设置筛选条件
        //未删除
        entity.setIsDelete(0);
        List<ActivityVotePeopleCommentPageInfo> list = activityVotePeopleCommentMapper.getActivityVotePeopleCommentPage(entity);
        return new TableResultResponse<ActivityVotePeopleCommentPageInfo>(result.getTotal(),list);
    }


}