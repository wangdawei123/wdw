package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmVideoTagBiz;
import com.kanfa.news.data.entity.XmVideoTag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmVideoTag")
public class XmVideoTagController extends BaseController<XmVideoTagBiz,XmVideoTag> {

}