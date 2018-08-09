package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ActivityVotePeopleImageBiz;
import com.kanfa.news.info.entity.ActivityVotePeopleImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activityVotePeopleImage")
public class ActivityVotePeopleImageController extends BaseController<ActivityVotePeopleImageBiz,ActivityVotePeopleImage> {

}