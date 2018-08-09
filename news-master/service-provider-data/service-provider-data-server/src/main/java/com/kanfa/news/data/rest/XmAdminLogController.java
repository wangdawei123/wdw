package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmAdminLogBiz;
import com.kanfa.news.data.entity.XmAdminLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmAdminLog")
public class XmAdminLogController extends BaseController<XmAdminLogBiz,XmAdminLog> {

}