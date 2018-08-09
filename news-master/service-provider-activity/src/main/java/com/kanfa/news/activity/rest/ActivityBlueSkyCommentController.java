package com.kanfa.news.activity.rest;

import com.kanfa.news.activity.biz.ActivityBlueSkyCommentBiz;
import com.kanfa.news.activity.entity.ActivityBlueSkyComment;
import com.kanfa.news.common.rest.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activityBlueSkyComment")
public class ActivityBlueSkyCommentController extends BaseController<ActivityBlueSkyCommentBiz,ActivityBlueSkyComment> {


    @Autowired
    private ActivityBlueSkyCommentBiz activityBlueSkyCommentBiz;

}