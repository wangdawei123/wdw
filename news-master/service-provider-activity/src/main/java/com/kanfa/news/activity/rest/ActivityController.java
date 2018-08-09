package com.kanfa.news.activity.rest;

import com.kanfa.news.activity.biz.ActivityBiz;
import com.kanfa.news.activity.entity.Activity;
import com.kanfa.news.common.rest.BaseController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("activity")
public class ActivityController extends BaseController<ActivityBiz,Activity> {

    @Autowired
    private ActivityBiz activityBiz;



}