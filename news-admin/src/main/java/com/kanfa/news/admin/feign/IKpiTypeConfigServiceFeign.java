package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.KpiTypeConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Chen
 * on 2018/5/2 15:16
 */
@FeignClient(name = "service-provider-news")
public interface IKpiTypeConfigServiceFeign {

    @RequestMapping(value = "/kpiTypeConfig/selectWorkTypeForDemand",method = RequestMethod.GET)
    List<KpiTypeConfig> selectWorkTypeForDemand();
}
