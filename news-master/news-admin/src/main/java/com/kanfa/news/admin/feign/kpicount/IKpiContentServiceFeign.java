package com.kanfa.news.admin.feign.kpicount;

import com.kanfa.news.admin.entity.KpiContent;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/17 10:51
 */
@FeignClient(name = "service-provider-news")
public interface IKpiContentServiceFeign {



    @GetMapping("/kpiContent/getListByCid")
    List<KpiContent> getListByCid(@RequestParam("contentId") Integer contentId);


}
