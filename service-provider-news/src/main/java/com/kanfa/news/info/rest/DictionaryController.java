package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.DictionaryBiz;
import com.kanfa.news.info.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dictionary")
public class DictionaryController extends BaseController<DictionaryBiz,Dictionary> {

    @Autowired
    private DictionaryBiz dictionaryBiz;
}