package com.kanfa.news.activity.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.activity.biz.ActivityRaffleBiz;
import com.kanfa.news.activity.entity.ActivityRaffle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activityRaffle")
public class ActivityRaffleController extends BaseController<ActivityRaffleBiz,ActivityRaffle> {

}