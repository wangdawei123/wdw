package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.BurstResourceBiz;
import com.kanfa.news.info.entity.BurstResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("burstResource")
public class BurstResourceController extends BaseController<BurstResourceBiz,BurstResource> {

}