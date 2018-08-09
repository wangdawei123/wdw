package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmContentImageBiz;
import com.kanfa.news.data.entity.XmContentImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xmContentImage")
public class XmContentImageController extends BaseController<XmContentImageBiz,XmContentImage> {

}