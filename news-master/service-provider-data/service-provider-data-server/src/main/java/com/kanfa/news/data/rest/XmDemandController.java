package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmDemandBiz;
import com.kanfa.news.data.entity.XmDemand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmDemand")
public class XmDemandController extends BaseController<XmDemandBiz,XmDemand> {

}