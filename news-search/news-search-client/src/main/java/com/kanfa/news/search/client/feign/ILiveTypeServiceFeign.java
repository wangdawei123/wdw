package com.kanfa.news.search.client.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.search.client.entity.LiveType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * @author Jezy
 */
@FeignClient(name = "service-provider-news")
public interface ILiveTypeServiceFeign {


    /**
     * 编辑直播类型
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/liveType/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<LiveType> get(@PathVariable("id") int id);


}
