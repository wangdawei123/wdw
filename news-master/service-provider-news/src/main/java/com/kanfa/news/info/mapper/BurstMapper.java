package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.Burst;
import com.kanfa.news.info.vo.admin.burst.BurstInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 新闻爆料表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 17:00:40
 */
public interface BurstMapper extends Mapper<Burst> {

    List<Map> selectBurstListByUid(@Param("uid") Integer uid);

    List<BurstInfo> getPage(Map<String, Object> params);

    BurstInfo getBurstInfoById(@Param("id") Integer id);

    int insertBurstSql(Burst appBurstInfo);
	
}
