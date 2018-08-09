package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ActivityBlueSkyImageBiz;
import com.kanfa.news.info.entity.ActivityBlueSkyImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activityBlueSkyImage")
public class ActivityBlueSkyImageController extends BaseController<ActivityBlueSkyImageBiz,ActivityBlueSkyImage> {

}