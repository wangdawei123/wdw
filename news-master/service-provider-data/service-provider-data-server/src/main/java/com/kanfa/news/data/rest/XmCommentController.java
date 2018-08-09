package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmCommentBiz;
import com.kanfa.news.data.entity.XmComment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmComment")
public class XmCommentController extends BaseController<XmCommentBiz,XmComment> {

}