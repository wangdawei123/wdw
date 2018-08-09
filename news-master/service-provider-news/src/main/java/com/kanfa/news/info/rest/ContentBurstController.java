package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ContentBurstBiz;
import com.kanfa.news.info.entity.ContentBurst;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contentBurst")
public class ContentBurstController extends BaseController<ContentBurstBiz,ContentBurst> {

}