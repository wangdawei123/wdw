package com.kanfa.news.admin.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Chen
 * on 2018/8/15 17:33
 */
@FeignClient(name = "service-provider-news")
public interface IContentVideoServiceFeign {


    @RequestMapping(value = "/contentVideo/updateIsRecommend",method = RequestMethod.GET)
    ObjectRestResponse updateIsRecommend(@RequestParam("contentId") Integer contentId,
                                         @RequestParam("isRecommend") Integer isRecommend);
}
