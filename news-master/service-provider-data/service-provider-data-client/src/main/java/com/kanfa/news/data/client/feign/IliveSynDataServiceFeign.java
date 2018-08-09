package com.kanfa.news.data.client.feign;

import com.kanfa.news.data.common.vo.live.LiveAddInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Chen
 * on 2018/8/7 12:36
 */
@FeignClient(name = "service-provider-data")
public interface IliveSynDataServiceFeign {
    /**
     * 根据id查询Live详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/xmLive/getLiveDetail",method = RequestMethod.GET)
    LiveAddInfo getLiveDetail(@RequestParam("id")Integer id);
}
