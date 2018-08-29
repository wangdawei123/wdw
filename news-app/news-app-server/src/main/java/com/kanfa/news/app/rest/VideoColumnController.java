package com.kanfa.news.app.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.app.biz.VideoColumnBiz;
import com.kanfa.news.app.entity.VideoColumn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("videoColumn")
public class VideoColumnController extends BaseController<VideoColumnBiz,VideoColumn> {

}