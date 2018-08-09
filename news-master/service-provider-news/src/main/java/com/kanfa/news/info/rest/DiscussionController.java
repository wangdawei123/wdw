package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.DiscussionBiz;
import com.kanfa.news.info.entity.Discussion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("discussion")
public class DiscussionController extends BaseController<DiscussionBiz,Discussion> {

}