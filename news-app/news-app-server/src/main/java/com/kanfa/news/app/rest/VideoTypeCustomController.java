package com.kanfa.news.app.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.app.biz.VideoTypeCustomBiz;
import com.kanfa.news.app.entity.VideoTypeCustom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("videoTypeCustom")
public class VideoTypeCustomController extends BaseController<VideoTypeCustomBiz,VideoTypeCustom> {

}