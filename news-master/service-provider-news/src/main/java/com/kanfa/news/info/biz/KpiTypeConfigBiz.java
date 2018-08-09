package com.kanfa.news.info.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.KpiTypeConfig;
import com.kanfa.news.info.mapper.KpiTypeConfigMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-03 16:37:55
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class KpiTypeConfigBiz extends BaseBiz<KpiTypeConfigMapper,KpiTypeConfig> {
    @Autowired
    private  KpiTypeConfigMapper kpiTypeConfigMapper;

    public List<KpiTypeConfig> selectWorkTypeForLive(){
        List<KpiTypeConfig> kpiTypeConfigs = kpiTypeConfigMapper.selectWorkTypeForLive();
        return kpiTypeConfigs;
    }

    public List<KpiTypeConfig> selectWorkTypeForDemand(){
        return  kpiTypeConfigMapper.selectWorkTypeForDemand();
    }

}