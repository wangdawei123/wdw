package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmCorpJobBiz;
import com.kanfa.news.data.entity.XmCorpJob;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmCorpJob")
public class XmCorpJobController extends BaseController<XmCorpJobBiz,XmCorpJob> {

}