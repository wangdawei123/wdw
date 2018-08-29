package com.kanfa.news.admin.mapper;

import com.kanfa.news.admin.entity.BurstType;
import com.kanfa.news.admin.vo.burst.BurstTypeInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 新闻爆料类型表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 17:00:40
 */
public interface BurstTypeMapper extends Mapper<BurstType> {

    List<BurstTypeInfo> getPage(Map<String, Object> params);
}
