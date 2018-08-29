package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.ContentArticle;
import com.kanfa.news.info.entity.ContentImage;
import com.kanfa.news.info.entity.ContentKpiCount;
import com.kanfa.news.info.entity.ContentVideo;
import com.kanfa.news.info.vo.admin.kpicount.ContentKpiCountInfo;
import com.kanfa.news.info.vo.admin.kpicount.ContentKpiInfo;
import com.kanfa.news.info.vo.admin.kpicount.KpiCountListInfo;
import com.kanfa.news.info.vo.admin.kpicount.KpiCountUserContentInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 发稿明细查询列表
 *
 * @author JiaYunwei
 * @email jiayunwei@kanfanews.com
 * @date 2018-06-20 17:52:20
 */
public interface ContentKpiCountMapper extends Mapper<ContentKpiCount> {

    List<ContentKpiCountInfo> getContentList(@Param("startDate") String startDate,
                                             @Param("endDate") String endDate,
                                             @Param("sourceType") Integer sourceType,
                                             @Param("viewType") Integer viewType);

    List<ContentKpiCount> getContent(@Param("startDate") String startDate,
                                    @Param("endDate") String endDate,
                                    @Param("contentId") Integer contentId);

    List<ContentKpiCount> getLive(@Param("startDate") String startDate,
                                  @Param("startDate") String endDate,
                                 @Param("liveId") Integer liveId);

    ContentArticle getOneContentArticle(@Param("contentId") Integer contentId);

    ContentImage getOneContentImage(@Param("contentId") Integer contentId);

    ContentVideo getOneContentVideo(@Param("contentId") Integer contentId);

    List<KpiCountListInfo> getKpiCountList(@Param("type") Integer type, @Param("typeId") Integer typeId);
}
