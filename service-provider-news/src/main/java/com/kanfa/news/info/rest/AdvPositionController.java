package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.AdvPositionBiz;
import com.kanfa.news.info.entity.AdvPosition;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("advPosition")
public class AdvPositionController extends BaseController<AdvPositionBiz,AdvPosition> {

}