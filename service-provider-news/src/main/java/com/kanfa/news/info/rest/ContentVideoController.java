package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ContentVideoBiz;
import com.kanfa.news.info.entity.ContentVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contentVideo")
public class ContentVideoController extends BaseController<ContentVideoBiz,ContentVideo> {

    @Autowired
    private ContentVideoBiz contentVideoBiz;

    @RequestMapping(value = "/updateIsRecommend",method = RequestMethod.GET)
    public ObjectRestResponse updateIsRecommend(@RequestParam("contentId") Integer contentId,
                                                @RequestParam("isRecommend") Integer isRecommend){
        ContentVideo contentVideo = new ContentVideo();
        contentVideo.setCid(contentId);
        contentVideo.setIsRecommend(isRecommend);
        contentVideo.setIsDelete(1);
        contentVideoBiz.updateSelectiveById(contentVideo);
        ObjectRestResponse<Object> objectObjectRestResponse = new ObjectRestResponse<>();
        objectObjectRestResponse.setRel(false);
        objectObjectRestResponse.setStatus(200);
        return objectObjectRestResponse;
    }

}