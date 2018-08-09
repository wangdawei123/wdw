package com.kanfa.news.activity.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.activity.biz.ActivityVoteContentBiz;
import com.kanfa.news.activity.entity.ActivityVoteContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activityVoteContent")
public class ActivityVoteContentController extends BaseController<ActivityVoteContentBiz,ActivityVoteContent> {

}