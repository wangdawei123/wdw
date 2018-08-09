package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.KpiContent;
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
public interface IKpiContentServiceFeign {

    @RequestMapping(value = "/kpiContent/getListByCid",method = RequestMethod.GET)
    List<KpiContent> getKpiContentList(@RequestParam("contentId") Integer contentId);
}
