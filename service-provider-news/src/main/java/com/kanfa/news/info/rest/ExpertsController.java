package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ExpertsBiz;
import com.kanfa.news.info.entity.Experts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("experts")
public class ExpertsController extends BaseController<ExpertsBiz,Experts> {

}