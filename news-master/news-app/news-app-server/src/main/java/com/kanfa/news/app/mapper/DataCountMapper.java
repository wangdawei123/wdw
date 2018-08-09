package com.kanfa.news.app.mapper;

import com.kanfa.news.app.vo.admin.kpicount.DataCountInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * 
 * @author Jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-27 11:32:57
 */
public interface DataCountMapper   {
    /**
     * 根据类型、频道、开始结束日期段，获取前100数据统计列表
     * @param categoryId
     * @param channelId
     * @param startDate
     * @param endDate
     * @return
     */
    List<DataCountInfo> dataCountTop100List(@Param("categoryId") Integer categoryId, @Param("channelId") Integer channelId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取全部浏览量
     * @return
     */
    Integer allViewContent();

    /**
     * 根据类型、频道获取发布量
     * @param categoryId
     * @param channelId
     * @return
     */
    Integer publishCount(@Param("categoryId") Integer categoryId, @Param("channelId") Integer channelId);


}
