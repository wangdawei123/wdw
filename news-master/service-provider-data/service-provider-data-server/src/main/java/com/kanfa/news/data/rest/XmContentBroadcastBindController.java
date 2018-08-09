package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmContentBroadcastBindBiz;
import com.kanfa.news.data.entity.XmContentBroadcastBind;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmContentBroadcastBind")
public class XmContentBroadcastBindController extends BaseController<XmContentBroadcastBindBiz,XmContentBroadcastBind> {

}