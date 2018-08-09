package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ActivityContentBind;
import com.kanfa.news.app.entity.Content;
import com.kanfa.news.app.vo.admin.activity.ActivityContentBindPageInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-18 14:17:51
 */
public interface ActivityContentBindMapper extends Mapper<ActivityContentBind> {

    List<ActivityContentBind> findByid(@Param("lawId") Integer lawId,
                                       @Param("offset") Integer offset,
                                       @Param("pcount") Integer pcount);

    /**
     * 政法先锋 关联内容
     * @param
     * @return
     */
    List<ActivityContentBindPageInfo> getActivityContentBindPage(ActivityContentBindPageInfo entity);


    /**
     * 政法先锋 通过标题查询文章 返回id title contentType
     * @param
     * @return
     */
    List<Content> getSearchPage(@Param("activityId") Integer activityId,
                                @Param("title") String title);


    /**
     * 政法先锋 通过activityId查maxOrderNum
     * @param
     * @return
     */
    Integer getMaxOrderNum(@Param("activityId") Integer activityId);
}
