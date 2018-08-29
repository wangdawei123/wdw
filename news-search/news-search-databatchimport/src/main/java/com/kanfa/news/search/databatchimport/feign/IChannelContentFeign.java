package com.kanfa.news.search.databatchimport.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/6/11 19:40
 */
@FeignClient(name = "service-provider-news")
public interface IChannelContentFeign {

    @GetMapping("/channelContent/getChannelContentForEs")
    List<Map<String,Object>> selectByContentIdFromType(@RequestParam("contentId") Integer contentId, @RequestParam("fromType") Integer fromType);
}
