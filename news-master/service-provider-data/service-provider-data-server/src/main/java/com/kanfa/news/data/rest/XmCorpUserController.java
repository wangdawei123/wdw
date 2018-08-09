package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmCorpUserBiz;
import com.kanfa.news.data.entity.XmCorpUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmCorpUser")
public class XmCorpUserController extends BaseController<XmCorpUserBiz,XmCorpUser> {

}