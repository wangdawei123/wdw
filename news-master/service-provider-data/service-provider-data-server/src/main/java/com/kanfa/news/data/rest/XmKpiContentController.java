package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmKpiContentBiz;
import com.kanfa.news.data.entity.XmKpiContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmKpiContent")
public class XmKpiContentController extends BaseController<XmKpiContentBiz,XmKpiContent> {

}