package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.DemandTypeBiz;
import com.kanfa.news.info.entity.DemandType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("demandType")
public class DemandTypeController extends BaseController<DemandTypeBiz,DemandType> {

}