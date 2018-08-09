package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmVideoTagBindBiz;
import com.kanfa.news.data.entity.XmVideoTagBind;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmVideoTagBind")
public class XmVideoTagBindController extends BaseController<XmVideoTagBindBiz,XmVideoTagBind> {

}