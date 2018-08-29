package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ContentImageBiz;
import com.kanfa.news.info.entity.ContentImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("contentImage")
public class ContentImageController extends BaseController<ContentImageBiz,ContentImage> {

    @Autowired
    private ContentImageBiz contentImageBiz;

    @RequestMapping(value = "/selectContentImageList",method = RequestMethod.POST)
    public List<ContentImage> selectContentImageList(@RequestBody ContentImage entity){
        return contentImageBiz.selectContentImageList(entity);
    }
}