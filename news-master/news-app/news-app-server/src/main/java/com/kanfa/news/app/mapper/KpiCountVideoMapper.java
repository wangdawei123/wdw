package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.KpiCount;
import com.kanfa.news.app.vo.admin.kpicount.*;
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
public interface KpiCountVideoMapper extends Mapper<KpiCount> {

    /**
     *  视频工作统计列表
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param pvEndDate pv统计结束日期
     * @param departmentId 部门ID
     * @param uid 员工ID
     * @return
     */

    List<KpiCountVideoInfo> listKpiCountVideo(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("pvEndDate") String pvEndDate, @Param("departmentId") Integer departmentId, @Param("uid") Integer uid);

    /**
     * 按日期时间段和UID查询视频列表
     * @param startDate
     * @param endDate
     * @param uid
     * @return
     */
    List<KpiVideoIdInfo> listKpiVideoId(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("uid") Integer uid);

    /**
     * 视频工作详情统计列表
     * @param startDate
     * @param endDate
     * @param uid
     * @param sourceTypeName
     * @param title
     * @return
     */
    List<KpiCountVideoDetailInfo> listKpiCountVideoDetail(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("uid") Integer uid, @Param("sourceTypeName") String sourceTypeName, @Param("title") String title);

    /**
     * 关联内容
     * @param uid
     * @return
     */
    List<KpiCountVideoDetailConnectContentInfo> listKpiCountVideoDetailConnectContent(@Param("uid") Integer uid);


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
