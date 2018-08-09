package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ContentVideoBiz;
import com.kanfa.news.info.entity.ContentVideo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("contentVideo")
public class ContentVideoController extends BaseController<ContentVideoBiz,ContentVideo> {

}