package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.KpiCount;
import com.kanfa.news.info.entity.User;
import com.kanfa.news.info.vo.admin.kpicount.KpiCountDetailInfo;
import com.kanfa.news.info.vo.admin.kpicount.KpiCountInfo;
import com.kanfa.news.info.vo.admin.kpicount.KpiTypeIdInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-14 11:46:04
 */
public interface KpiCountMapper extends Mapper<KpiCount> {
    /**
     * 分页查询  记者工作统计
     * @param startDate  开始日期
     * @param endDate   线束日期
     * @param departmentId  部门id
     * @param uid  人员id
     * @return
     */
    List<KpiCountInfo> listKpiCount(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("departmentId") Integer departmentId,@Param("uid")Integer uid);

    /**
     * 获取类型列表
     * @param startDate
     * @param endDate
     * @param uid
     * @param departmentId
     * @return
     */
    List<KpiTypeIdInfo> listKpiTypeId(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("uid")Integer uid, @Param("departmentId") Integer departmentId);

    /**
     * 分页查询  记者工作详情统计
     * @param startDate
     * @param endDate
     * @param uid
     * @param title
     * @param articleTypeName
     * @return
     */
    List<KpiCountDetailInfo> listKpiCountDetail(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("uid")Integer uid, @Param("articleTypeName") String articleTypeName,@Param("title") String title);

    /**
     *查询7日内未达标的视频直播id
     * @param startDate
     * @param endDate
     * @return
     */
    List<KpiCount> selectUnStandard(@Param("startDate") String startDate, @Param("endDate") String endDate);

    void updateKpiCount(@Param("finishTime") String finishTime,@Param("id")Integer id);
}
