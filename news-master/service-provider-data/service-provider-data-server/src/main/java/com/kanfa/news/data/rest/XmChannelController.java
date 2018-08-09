package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmChannelBiz;
import com.kanfa.news.data.entity.XmChannel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmChannel")
public class XmChannelController extends BaseController<XmChannelBiz,XmChannel> {

}