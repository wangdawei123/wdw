package com.kanfa.news.app.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/30 18:02
 */
public interface ChartDataMapper {
    /**
     * 日发稿量排行
     * @param startDate
     * @param endDate
     * @return
     */
    List<Map<String,Object>> pressTop(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取原创非原创列表
     * @param startDate
     * @param endDate
     * @return
     */
    List<Map<String,Object>> originalOrNo(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 直播统计
     * @param startDate
     * @param endDate
     * @return
     */
    List<Map<String,Object>> liveCount(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取标题列表
     * @param ids
     * @return
     */
    List<Map<String,Object>> getTitle(@Param("list") List<Integer> ids);

    List<Map<String,Object>> getContentData(@Param("ids") List<String> ids);

    List<Map<String,Object>> getLiveData(@Param("ids") List<String> ids);

    List<Map<String,Object>> getHoursMysqlData(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
