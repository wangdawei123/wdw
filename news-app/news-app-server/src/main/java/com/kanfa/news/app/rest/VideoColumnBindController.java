package com.kanfa.news.app.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.app.biz.VideoColumnBindBiz;
import com.kanfa.news.app.entity.VideoColumnBind;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("videoColumnBind")
public class VideoColumnBindController extends BaseController<VideoColumnBindBiz,VideoColumnBind> {

}