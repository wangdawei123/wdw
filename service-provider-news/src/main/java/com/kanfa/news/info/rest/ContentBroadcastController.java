package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ContentBroadcastBiz;
import com.kanfa.news.info.entity.ContentBroadcast;
import com.kanfa.news.info.vo.admin.live.ContentBroadcastInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contentBroadcast")
public class ContentBroadcastController extends BaseController<ContentBroadcastBiz,ContentBroadcast> {

    @Autowired
    private ContentBroadcastBiz contentBroadcastBiz;

    /**
     * 分页查询aliyun列表
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    public TableResultResponse<ContentBroadcastInfo> getPage(@RequestBody ContentBroadcastInfo entity) {
        return contentBroadcastBiz.getPage(entity);
    }
}