package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmContentSpecialBiz;
import com.kanfa.news.data.entity.XmContentSpecial;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmContentSpecial")
public class XmContentSpecialController extends BaseController<XmContentSpecialBiz,XmContentSpecial> {

}