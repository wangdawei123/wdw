package com.kanfa.news.user.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jezy
 */
@FeignClient(name = "service-provider-common")
public interface RedisClient {
    /**
     * 文件上传
     *
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/redis/setString")
    ObjectRestResponse set(@RequestParam(name = "key") String key ,@RequestParam(name = "value") String value);
}