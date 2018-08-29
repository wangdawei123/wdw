package com.kanfa.news.admin.feign;

/**
 * Created by Chen
 * on 2018/4/19 16:43
 */

import com.kanfa.news.admin.vo.activity.ActiveAwardResultInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-provider-news")
public interface IActiveAwardResultServiceFeign {

    @RequestMapping(value = "/activeAwardResult/getActiveAwardResult", method = RequestMethod.GET)
    List<ActiveAwardResultInfo> getActiveAwardResult(@RequestParam("activityRaffleId")Integer activityRaffleId);
}
