package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmContentVrBiz;
import com.kanfa.news.data.entity.XmContentVr;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmContentVr")
public class XmContentVrController extends BaseController<XmContentVrBiz,XmContentVr> {

}