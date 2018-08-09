package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.ActiveAwardResult;
import com.kanfa.news.info.vo.admin.activity.ActiveAwardResultInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-19 10:48:54
 */
public interface ActiveAwardResultMapper extends Mapper<ActiveAwardResult> {

    List<ActiveAwardResultInfo> getActiveAwardResult(@Param("activityRaffleId")Integer activityRaffleId);
	
}
