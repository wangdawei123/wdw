package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmCorpDeptBiz;
import com.kanfa.news.data.entity.XmCorpDept;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmCorpDept")
public class XmCorpDeptController extends BaseController<XmCorpDeptBiz,XmCorpDept> {

}