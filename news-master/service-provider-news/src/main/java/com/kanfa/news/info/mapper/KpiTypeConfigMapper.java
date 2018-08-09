package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.KpiTypeConfig;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-03 16:37:55
 */
public interface KpiTypeConfigMapper extends Mapper<KpiTypeConfig> {

    //Live里的工作类型
    List<KpiTypeConfig> selectWorkTypeForLive();
    // 获得达标配置
    List<KpiTypeConfig> selectIdLimit();

    //视频上传里的工作类型
    List<KpiTypeConfig> selectWorkTypeForDemand();
	
}
