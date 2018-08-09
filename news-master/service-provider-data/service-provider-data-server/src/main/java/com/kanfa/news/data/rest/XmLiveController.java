package com.kanfa.news.data.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.data.biz.XmLiveBiz;
import com.kanfa.news.data.common.vo.live.LiveAddInfo;
import com.kanfa.news.data.entity.XmLive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("xmLive")
public class XmLiveController extends BaseController<XmLiveBiz,XmLive> {

    @Autowired
    private XmLiveBiz xmLiveBiz;

    @RequestMapping(value = "/getLiveDetail",method = RequestMethod.GET)
    public LiveAddInfo addOneLive(@RequestParam("id")Integer id){
        return xmLiveBiz.getOneLive(id);
    }


}