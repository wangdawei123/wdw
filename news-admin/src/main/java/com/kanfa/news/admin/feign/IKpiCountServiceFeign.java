package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.KpiCount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/14 14:13
 */
@FeignClient(name = "service-provider-news")
public interface IKpiCountServiceFeign {

    @RequestMapping(value = "/kpiCount/getListByCid",method = RequestMethod.GET)
    List<KpiCount> getKpiContentList(@RequestParam("contentId") Integer contentId);
}
