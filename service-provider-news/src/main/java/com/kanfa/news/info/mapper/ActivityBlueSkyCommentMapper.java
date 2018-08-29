package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.ActivityBlueSkyComment;
import com.kanfa.news.info.vo.admin.activity.ActivityBlueSkyCommentPageInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-17 11:23:12
 */
public interface ActivityBlueSkyCommentMapper extends Mapper<ActivityBlueSkyComment> {


    /**
     * 候选人评论列表 的分页及按审核状态搜索
     * @param
     * @return
     */
    List<ActivityBlueSkyCommentPageInfo> getActivityBlueSkyCommentPage(ActivityBlueSkyCommentPageInfo entity);
}
