package com.kanfa.news.activity.mapper;


import com.kanfa.news.activity.entity.ActiveAwardResult;
import com.kanfa.news.activity.vo.info.ActivityAwardResultInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-20 15:34:40
 */
public interface ActiveAwardResultMapper extends Mapper<ActiveAwardResult> {

    List<ActivityAwardResultInfo> getMessage(@Param("uid") Integer uid);
	
}
