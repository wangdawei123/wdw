package com.kanfa.news.activity.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.activity.biz.ActivityVotePeopleBiz;
import com.kanfa.news.activity.entity.ActivityVotePeople;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activityVotePeople")
public class ActivityVotePeopleController extends BaseController<ActivityVotePeopleBiz,ActivityVotePeople> {

}