package com.kanfa.news.activity.rest;

import com.kanfa.news.activity.biz.ActiveAwardResultBiz;
import com.kanfa.news.activity.entity.ActiveAwardResult;
import com.kanfa.news.common.rest.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activeAwardResult")
public class ActiveAwardResultController extends BaseController<ActiveAwardResultBiz,ActiveAwardResult> {

}