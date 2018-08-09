package com.kanfa.news.info.mapper;

import com.kanfa.news.info.vo.admin.kpicount.ContentUserInfo;
import com.kanfa.news.info.vo.admin.kpicount.KpiCountUserContentInfo;
import com.kanfa.news.info.vo.admin.kpicount.LiveUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 人员发布查询表
 *
 * @author JiaYunwei
 * @email jiayunwei@kanfanews.com
 * @date 2018-06-20 17:52:20
 */
public interface KpiCountUserContentMapper {

    List<KpiCountUserContentInfo> getCountUserContent(@Param("startDate") String startDate,
                                                      @Param("endDate") String endDate,
                                                      @Param("dayShow") Integer dayShow,
                                                      @Param("uid") Integer uid,
                                                      @Param("sourceType") Integer sourceType,
                                                      @Param("dutyType") Integer dutyType
    );

    List<ContentUserInfo> getContent(@Param("startDate") String startDate,
                                     @Param("endDate") String endDate);

    List<LiveUserInfo> getLive(@Param("startDate") String startDate,
                               @Param("endDate") String endDate);
}
