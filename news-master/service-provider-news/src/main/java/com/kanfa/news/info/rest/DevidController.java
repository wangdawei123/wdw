package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.DevidBiz;
import com.kanfa.news.info.entity.Devid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("devid")
public class DevidController extends BaseController<DevidBiz,Devid> {

}