package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmContentArticleBiz;
import com.kanfa.news.data.entity.XmContentArticle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmContentArticle")
public class XmContentArticleController extends BaseController<XmContentArticleBiz,XmContentArticle> {

}