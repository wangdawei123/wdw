package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmContentPreviewBiz;
import com.kanfa.news.data.entity.XmContentPreview;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmContentPreview")
public class XmContentPreviewController extends BaseController<XmContentPreviewBiz,XmContentPreview> {

}