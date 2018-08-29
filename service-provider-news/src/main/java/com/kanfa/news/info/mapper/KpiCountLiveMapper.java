package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.KpiCount;
import com.kanfa.news.info.vo.admin.kpicount.*;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-17 11:46:04
 */
public interface KpiCountLiveMapper extends Mapper<KpiCount> {

    /**
     * 直播工作统计列表
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param departmentId 部门ID
     * @param uid 员工ID
     * @return
     */
    public List<KpiCountLiveInfo> listKpiCountLive(@Param("startDate") String startDate, @Param("endDate") String endDate,  @Param("departmentId") Integer departmentId, @Param("uid") Integer uid);

    /**
     * 根据日期段和UID查询直播列表
     * @param startDate
     * @param endDate
     * @param uid
     * @return
     */
    public List<KpiLiveIdInfo> listKpiLiveId(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("uid") Integer uid);

    /**
     * 视频工作详情统计列表
     * @param startDate
     * @param endDate
     * @param uid
     * @param title
     * @return
     */
     List<KpiCountLiveDetailInfo> listKpiCountLiveDetail(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("uid") Integer uid, @Param("liveTypeName") String liveTypeName, @Param("title") String title);

    /**
     * 工作类型列表
     * @return
     */
     List<KpiCountWorkTypeInfo> listWorkType();

    /**
     * 按工作类型统计结果列表
     * @param startDate
     * @param endDate
     * @return
     */
     List<KpiCountWorkTypeResultInfo> listWorkTypeResult(@Param("startDate") String startDate, @Param("endDate") String endDate);

   }
