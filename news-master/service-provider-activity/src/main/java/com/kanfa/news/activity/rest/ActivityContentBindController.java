package com.kanfa.news.activity.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.activity.biz.ActivityContentBindBiz;
import com.kanfa.news.activity.entity.ActivityContentBind;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activityContentBind")
public class ActivityContentBindController extends BaseController<ActivityContentBindBiz,ActivityContentBind> {

}