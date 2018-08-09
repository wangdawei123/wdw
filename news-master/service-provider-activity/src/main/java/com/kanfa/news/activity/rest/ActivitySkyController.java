package com.kanfa.news.activity.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.activity.biz.ActivitySkyBiz;
import com.kanfa.news.activity.entity.ActivitySky;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activitySky")
public class ActivitySkyController extends BaseController<ActivitySkyBiz,ActivitySky> {

}