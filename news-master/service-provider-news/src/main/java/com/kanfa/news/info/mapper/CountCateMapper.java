package com.kanfa.news.info.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 每日类目文章统计
 * 
 * @author Jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-05-08 16:52:18
 */
public interface CountCateMapper  {

    /*类目统计*/
    Map<String,Object> categoryCount(@Param("type")Integer type,@Param("startDate") String startDate, @Param("endDate") String endDate);

    /*类目+来源统计*/
    Map<String,Object> categorySourceCount(@Param("type")Integer type,@Param("sourceType")Integer sourceType,@Param("startDate") String startDate, @Param("endDate") String endDate);

    /*类目+法制类统计*/
    Map<String,Object> categoryLegalCount(@Param("type")Integer type,@Param("isLegal")Integer isLegal,@Param("startDate") String startDate, @Param("endDate") String endDate);

}
