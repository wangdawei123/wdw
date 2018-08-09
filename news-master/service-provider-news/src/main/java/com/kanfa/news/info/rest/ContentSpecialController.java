package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ContentSpecialBiz;
import com.kanfa.news.info.entity.ContentSpecial;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contentSpecial")
public class ContentSpecialController extends BaseController<ContentSpecialBiz,ContentSpecial> {

}