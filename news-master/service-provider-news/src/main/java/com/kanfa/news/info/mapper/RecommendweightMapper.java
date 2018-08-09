package com.kanfa.news.info.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/14 14:24
 */
public interface RecommendweightMapper {

   void recommendweightStart(@Param("startDate")String startDate,@Param("endDate")String endDate);

   void  recommendweightLive(@Param("endDate")String endDate);
}
