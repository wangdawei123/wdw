package com.kanfa.news.activity.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.activity.biz.ActivityVotePeopleCommentBiz;
import com.kanfa.news.activity.entity.ActivityVotePeopleComment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activityVotePeopleComment")
public class ActivityVotePeopleCommentController extends BaseController<ActivityVotePeopleCommentBiz,ActivityVotePeopleComment> {

}