package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ExtendChannelBiz;
import com.kanfa.news.info.entity.ExtendChannel;
import com.kanfa.news.info.vo.admin.activity.ExtendChannelPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("extendChannel")
public class ExtendChannelController extends BaseController<ExtendChannelBiz,ExtendChannel> {


    @Autowired
    private ExtendChannelBiz extendChannelBiz;



    /**
     * 推广渠道的分页及查询
     * @return
     */
    @RequestMapping(value = "/getExtendChannelPage", method = RequestMethod.POST)
    public TableResultResponse<ExtendChannelPageInfo> getExtendChannelPage(@RequestBody ExtendChannelPageInfo entity) {
        return extendChannelBiz.getExtendChannelPage(entity);
    }


    @RequestMapping(value = "/extendChannelIdNames",method = RequestMethod.GET)
    public List<ExtendChannel> extendChannelIdNames(){
        return  extendChannelBiz.extendChannelIdNames();
    }
}