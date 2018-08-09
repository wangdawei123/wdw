package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmContentStandardBiz;
import com.kanfa.news.data.entity.XmContentStandard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmContentStandard")
public class XmContentStandardController extends BaseController<XmContentStandardBiz,XmContentStandard> {

}