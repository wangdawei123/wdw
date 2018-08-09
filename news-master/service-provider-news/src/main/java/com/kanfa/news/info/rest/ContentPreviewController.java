package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ContentPreviewBiz;
import com.kanfa.news.info.entity.ContentPreview;
import com.kanfa.news.info.vo.admin.info.ContentPreviewInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contentPreview")
public class ContentPreviewController extends BaseController<ContentPreviewBiz,ContentPreview> {

    @Autowired
    private ContentPreviewBiz  contentPreviewBiz;


    /**
     * 添加预览
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addPreviewArticle",method = RequestMethod.POST)
    public ObjectRestResponse<ContentPreviewInfo> addPreviewArticle(ContentPreviewInfo entity){
        ObjectRestResponse restResponse=new ObjectRestResponse();
        contentPreviewBiz.addPreviewArticle(entity);
        restResponse.setData(entity);
        return restResponse;
    }
}