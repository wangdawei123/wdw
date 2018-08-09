package com.kanfa.news.activity.rest;

import com.kanfa.news.activity.biz.ActivityBlueSkyBiz;
import com.kanfa.news.activity.biz.ActivityBlueSkyImageBiz;
import com.kanfa.news.activity.entity.ActivityBlueSky;
import com.kanfa.news.common.rest.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("activityBlueSky")
public class ActivityBlueSkyController extends BaseController<ActivityBlueSkyBiz,ActivityBlueSky> {

    @Autowired
    private ActivityBlueSkyBiz activityBlueSkyBiz;
    @Autowired
    private ActivityBlueSkyImageBiz activityBlueSkyImageBiz;


    }