package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ContentImageGroupBiz;
import com.kanfa.news.info.entity.ContentImageGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("contentImageGroup")
public class ContentImageGroupController extends BaseController<ContentImageGroupBiz,ContentImageGroup> {

    @Autowired
    private ContentImageGroupBiz contentImageGroupBiz;

    @ResponseBody
    @RequestMapping(value = "/selectImageGroupList",method = RequestMethod.POST)
    public List<ContentImageGroup> selectImageGroupList(){
        return contentImageGroupBiz.selectImageGroupList();
    }
}