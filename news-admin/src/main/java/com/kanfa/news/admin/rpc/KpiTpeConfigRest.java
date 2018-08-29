package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.KpiTypeConfig;
import com.kanfa.news.admin.feign.IKpiTypeConfigServiceFeign;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Chen
 * on 2018/5/2 15:23
 */

@RestController
@RequestMapping("kpiTypeConfig")
public class KpiTpeConfigRest {

    @Autowired
    private IKpiTypeConfigServiceFeign kpiTypeConfigServiceFeign;

    @RequestMapping(value = "/selectWorkTypeForDemand",method = RequestMethod.GET)
    public ObjectRestResponse<List<KpiTypeConfig>>  selectWorkTypeForDemand(){
        List<KpiTypeConfig> kpiTypeConfigs = kpiTypeConfigServiceFeign.selectWorkTypeForDemand();
        ObjectRestResponse<List<KpiTypeConfig>> listObjectRestResponse = new ObjectRestResponse<>();
        listObjectRestResponse.setData(kpiTypeConfigs);
        return  listObjectRestResponse;
    }
}
