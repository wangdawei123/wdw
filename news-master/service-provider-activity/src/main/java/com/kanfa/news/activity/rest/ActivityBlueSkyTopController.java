package com.kanfa.news.activity.rest;

import com.kanfa.news.activity.biz.ActivityBlueSkyTopBiz;
import com.kanfa.news.activity.entity.ActivityBlueSkyTop;
import com.kanfa.news.common.rest.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activityBlueSkyTop")
public class ActivityBlueSkyTopController extends BaseController<ActivityBlueSkyTopBiz,ActivityBlueSkyTop> {

}