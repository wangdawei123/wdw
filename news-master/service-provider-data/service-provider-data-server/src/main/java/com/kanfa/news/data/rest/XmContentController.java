package com.kanfa.news.data.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmChannelBiz;
import com.kanfa.news.data.biz.XmContentBiz;
import com.kanfa.news.data.common.vo.channel.ContentInfo;
import com.kanfa.news.data.entity.XmContent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import com.kanfa.news.data.common.vo.video.VideoContentInfo;
import com.kanfa.news.data.common.vo.vr.VRContentAddInfo;
import com.kanfa.news.data.entity.XmContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("xmContent")
public class XmContentController extends BaseController<XmContentBiz,XmContent> {

    @Autowired
    private XmContentBiz xmContentBiz;
    @Autowired
    private XmChannelBiz xmChannelBiz;

    @RequestMapping(value = "/getContentInfo/{id}",method = RequestMethod.GET)
    public ObjectRestResponse<ContentInfo> getContentInfo(@PathVariable int id) {
        ContentInfo contentInfo=this.baseBiz.getContentInfo(id);
        ObjectRestResponse<ContentInfo> objectRestResponse = new ObjectRestResponse<>();
        objectRestResponse.setData(contentInfo);
        return objectRestResponse;
    }

    /**
     * 得到视频详尽信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getVideoDetail",method = RequestMethod.GET)
    VideoContentInfo getVideoDetail(@RequestParam("id")Integer id){
        return xmContentBiz.getVideoDetail(id);
    }

    /**
     * 得到vr详尽信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getVrDetail",method = RequestMethod.GET)
    VRContentAddInfo getVrDetail(@RequestParam("id")Integer id){
        return xmContentBiz.getVrDetail(id);
    }

}