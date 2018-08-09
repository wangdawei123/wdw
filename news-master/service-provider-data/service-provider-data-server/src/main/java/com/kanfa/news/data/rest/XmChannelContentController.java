package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmChannelContentBiz;
import com.kanfa.news.data.entity.XmChannelContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmChannelContent")
public class XmChannelContentController extends BaseController<XmChannelContentBiz,XmChannelContent> {

}