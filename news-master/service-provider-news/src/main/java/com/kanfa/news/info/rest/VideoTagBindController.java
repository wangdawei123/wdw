package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.VideoTagBindBiz;
import com.kanfa.news.info.entity.VideoTagBind;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("videoTagBind")
public class VideoTagBindController extends BaseController<VideoTagBindBiz,VideoTagBind> {

}