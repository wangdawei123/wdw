package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ActivityVoteBiz;
import com.kanfa.news.info.entity.ActivityVote;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activityVote")
public class ActivityVoteController extends BaseController<ActivityVoteBiz,ActivityVote> {

}