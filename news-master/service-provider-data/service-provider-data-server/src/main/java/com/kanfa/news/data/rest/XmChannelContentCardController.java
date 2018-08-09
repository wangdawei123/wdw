package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmChannelContentCardBiz;
import com.kanfa.news.data.entity.XmChannelContentCard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmChannelContentCard")
public class XmChannelContentCardController extends BaseController<XmChannelContentCardBiz,XmChannelContentCard> {

}