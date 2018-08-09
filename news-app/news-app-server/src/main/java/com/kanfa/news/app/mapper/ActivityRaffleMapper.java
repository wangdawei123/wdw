package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ActivityRaffle;
import com.kanfa.news.app.vo.admin.activity.ActivityRafflePageInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 20:02:37
 */
public interface ActivityRaffleMapper extends Mapper<ActivityRaffle> {
    /**
     * 查询判断当前是否有活动
     * @return
     */
    List<ActivityRaffle> getNowActivityRaffle(@Param("startTime") Date startTime);


    /**
     * 抽奖活动的分页及搜索
     * @return
     */
    List<ActivityRafflePageInfo> getActivityRafflePage(ActivityRafflePageInfo entity);


}
