package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmContentBurstBiz;
import com.kanfa.news.data.entity.XmContentBurst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmContentBurst")
public class XmContentBurstController extends BaseController<XmContentBurstBiz,XmContentBurst> {

}