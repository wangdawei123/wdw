package com.kanfa.news.activity.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.activity.biz.ActivityVoteBiz;
import com.kanfa.news.activity.entity.ActivityVote;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activityVote")
public class ActivityVoteController extends BaseController<ActivityVoteBiz,ActivityVote> {

}