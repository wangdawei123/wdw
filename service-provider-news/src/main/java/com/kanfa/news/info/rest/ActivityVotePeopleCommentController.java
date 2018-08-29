package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ActivityVotePeopleCommentBiz;
import com.kanfa.news.info.entity.ActivityVotePeopleComment;
import com.kanfa.news.info.vo.admin.activity.ActivityVotePeopleCommentPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activityVotePeopleComment")
public class ActivityVotePeopleCommentController extends BaseController<ActivityVotePeopleCommentBiz,ActivityVotePeopleComment> {

    @Autowired
    private ActivityVotePeopleCommentBiz activityVotePeopleCommentBiz;

    /**
     * 候选人评论列表 的分页及按审核状态搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getActivityVotePeopleCommentPage", method = RequestMethod.POST)
    public TableResultResponse<ActivityVotePeopleCommentPageInfo> getActivityVotePeopleCommentPage(@RequestBody ActivityVotePeopleCommentPageInfo entity) {
        return activityVotePeopleCommentBiz.getActivityVotePeopleCommentPage(entity);
    }

}