package com.kanfa.news.app.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author Administrator
 */
@FeignClient(name = "service-provider-news")
public interface IAdvertisementConfigServiceFeign {
    /**
     * 文章详情页接口
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/advertisementConfig/all", method = RequestMethod.POST)
    ObjectRestResponse getArticleDetail(@RequestBody Map<String, Object> entity);
}
