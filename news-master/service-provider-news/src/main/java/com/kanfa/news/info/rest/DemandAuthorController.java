package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.DemandAuthorBiz;
import com.kanfa.news.info.entity.DemandAuthor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("demandAuthor")
public class DemandAuthorController extends BaseController<DemandAuthorBiz,DemandAuthor> {

}