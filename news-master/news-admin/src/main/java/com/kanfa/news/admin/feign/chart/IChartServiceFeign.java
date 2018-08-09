package com.kanfa.news.admin.feign.chart;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/20 15:29
 */
@FeignClient(name = "service-provider-news")
public interface IChartServiceFeign {
    @RequestMapping("chart/getData")
    Map<String,Object> getData(@RequestParam("days")Integer days);
}
