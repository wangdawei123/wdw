package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ActivityAwardBiz;
import com.kanfa.news.info.entity.ActivityAward;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activityAward")
public class ActivityAwardController extends BaseController<ActivityAwardBiz,ActivityAward> {

}