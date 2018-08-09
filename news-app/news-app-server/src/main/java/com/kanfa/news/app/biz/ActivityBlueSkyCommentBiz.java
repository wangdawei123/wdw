package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.ActivityBlueSkyComment;
import com.kanfa.news.app.mapper.ActivityBlueSkyCommentMapper;
import com.kanfa.news.app.vo.admin.activity.ActivityBlueSkyCommentPageInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-17 11:23:12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityBlueSkyCommentBiz extends BaseBiz<ActivityBlueSkyCommentMapper,ActivityBlueSkyComment> {


    @Autowired
    private ActivityBlueSkyCommentMapper activityBlueSkyCommentMapper;

    /**
     * 候选人评论列表 的分页及按审核状态搜索
     * @param
     * @return
     */
    public TableResultResponse<ActivityBlueSkyCommentPageInfo> getActivityBlueSkyCommentPage(ActivityBlueSkyCommentPageInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //未删除 0 已删除 1
        entity.setIsDelete(0);
        List<ActivityBlueSkyCommentPageInfo> list = activityBlueSkyCommentMapper.getActivityBlueSkyCommentPage(entity);
        return new TableResultResponse<ActivityBlueSkyCommentPageInfo>(result.getTotal(),list);
    }
}