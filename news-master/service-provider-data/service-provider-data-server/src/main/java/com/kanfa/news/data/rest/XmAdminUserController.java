package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmAdminUserBiz;
import com.kanfa.news.data.entity.XmAdminUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmAdminUser")
public class XmAdminUserController extends BaseController<XmAdminUserBiz,XmAdminUser> {

}