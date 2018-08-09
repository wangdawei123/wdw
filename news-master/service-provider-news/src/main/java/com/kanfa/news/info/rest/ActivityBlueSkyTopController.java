package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ActivityBlueSkyTopBiz;
import com.kanfa.news.info.entity.ActivityBlueSkyTop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activityBlueSkyTop")
public class ActivityBlueSkyTopController extends BaseController<ActivityBlueSkyTopBiz,ActivityBlueSkyTop> {

}