package com.kanfa.news.admin.mapper;

import com.kanfa.news.admin.entity.Burst;
import com.kanfa.news.admin.vo.burst.BurstInfo;
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
