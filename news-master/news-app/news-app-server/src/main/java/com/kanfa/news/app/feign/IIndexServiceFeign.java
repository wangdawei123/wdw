package com.kanfa.news.app.feign;

import com.kanfa.news.common.msg.AppObjectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-provider-news")
public interface IIndexServiceFeign {

    /**
     *  app首页搜索
     */
    @RequestMapping(value = "/search/nGetSearchList", method = RequestMethod.POST)
    AppObjectResponse nGetSearchList(@RequestParam("keyword") String keyword,
                                     @RequestParam(value = "id", required = false) Integer id,
                                     @RequestParam(value = "page") Integer page,
                                     @RequestParam(value = "pcount") Integer pcount,
                                     @RequestParam(value = "type") Integer type);

    @RequestMapping(value = "/search/getIndexTopArea", method = RequestMethod.POST)
    AppObjectResponse getIndexTopArea();

}
