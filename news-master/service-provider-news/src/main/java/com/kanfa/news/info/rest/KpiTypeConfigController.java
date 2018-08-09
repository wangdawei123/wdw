package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.KpiTypeConfigBiz;
import com.kanfa.news.info.entity.KpiTypeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("kpiTypeConfig")
public class KpiTypeConfigController extends BaseController<KpiTypeConfigBiz,KpiTypeConfig> {

    @Autowired
    private KpiTypeConfigBiz kpiTypeConfigBiz;

    @RequestMapping(value = "/selectWorkTypeForLive",method = RequestMethod.GET)
    public List<KpiTypeConfig> selectWorkTypeForLive(){
        return kpiTypeConfigBiz.selectWorkTypeForLive();
    }


    @RequestMapping(value = "/selectWorkTypeForDemand",method = RequestMethod.GET)
    public List<KpiTypeConfig> selectWorkTypeForDemand(){
        return  kpiTypeConfigBiz.selectWorkTypeForDemand();
    }


}