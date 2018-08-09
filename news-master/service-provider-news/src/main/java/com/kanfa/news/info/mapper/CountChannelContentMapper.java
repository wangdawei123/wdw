package com.kanfa.news.info.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/7 16:43
 */
public interface CountChannelContentMapper {
    List<Map<String,Object>> listChannelContent(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
