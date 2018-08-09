package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ContentStandardBiz;
import com.kanfa.news.info.entity.ContentStandard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("contentStandard")
public class ContentStandardController extends BaseController<ContentStandardBiz,ContentStandard> {

}