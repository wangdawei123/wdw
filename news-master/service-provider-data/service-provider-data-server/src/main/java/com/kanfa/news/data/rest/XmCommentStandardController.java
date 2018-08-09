package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmCommentStandardBiz;
import com.kanfa.news.data.entity.XmCommentStandard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmCommentStandard")
public class XmCommentStandardController extends BaseController<XmCommentStandardBiz,XmCommentStandard> {

}