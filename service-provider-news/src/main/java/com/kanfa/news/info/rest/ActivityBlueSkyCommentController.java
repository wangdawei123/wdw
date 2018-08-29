package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ActivityBlueSkyCommentBiz;
import com.kanfa.news.info.entity.ActivityBlueSkyComment;
import com.kanfa.news.info.vo.admin.activity.ActivityBlueSkyCommentPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activityBlueSkyComment")
public class ActivityBlueSkyCommentController extends BaseController<ActivityBlueSkyCommentBiz,ActivityBlueSkyComment> {


    @Autowired
    private ActivityBlueSkyCommentBiz activityBlueSkyCommentBiz;

    /**
     * 候选人评论列表 的分页及按审核状态搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getActivityBlueSkyCommentPage", method = RequestMethod.POST)
    public TableResultResponse<ActivityBlueSkyCommentPageInfo> getActivityBlueSkyCommentPage(@RequestBody ActivityBlueSkyCommentPageInfo entity) {
        return activityBlueSkyCommentBiz.getActivityBlueSkyCommentPage(entity);
    }
}