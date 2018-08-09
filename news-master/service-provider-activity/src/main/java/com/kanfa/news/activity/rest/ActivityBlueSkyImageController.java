package com.kanfa.news.activity.rest;

import com.kanfa.news.activity.biz.ActivityBlueSkyImageBiz;
import com.kanfa.news.activity.entity.ActivityBlueSkyImage;
import com.kanfa.news.common.rest.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activityBlueSkyImage")
public class ActivityBlueSkyImageController extends BaseController<ActivityBlueSkyImageBiz,ActivityBlueSkyImage> {

}