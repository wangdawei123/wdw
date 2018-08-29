package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.ChannelContent;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/8/10 14:53
 */

@FeignClient(name = "service-provider-news")
public interface IChannelContentServiceFeign {

    //保存视频分类
    @RequestMapping(value = "/channelContent/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<ChannelContent> update(@PathVariable("id") Integer id, @RequestBody ChannelContent entity);
}
