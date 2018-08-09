package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.CommentBadwordsBiz;
import com.kanfa.news.info.entity.CommentBadwords;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("commentBadwords")
public class CommentBadwordsController extends BaseController<CommentBadwordsBiz,CommentBadwords> {

}