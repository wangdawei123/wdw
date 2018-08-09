package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmContentVideoBiz;
import com.kanfa.news.data.entity.XmContentVideo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmContentVideo")
public class XmContentVideoController extends BaseController<XmContentVideoBiz,XmContentVideo> {

}