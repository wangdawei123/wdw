package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ActivityBlueSky;
import com.kanfa.news.app.vo.admin.activity.ActivityBlueSkyPageInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-17 11:23:12
 */
public interface ActivityBlueSkyMapper extends Mapper<ActivityBlueSky> {

    /**
     * 青春微普法大赛活动 候选人新增
     * @param
     * @return
     */
    int insertActivityBlueSky(ActivityBlueSky entity);

    //勿动 有用
    ActivityBlueSky selectOneActivityBlueSky(@Param("id") Integer id);

    /**
     * 青春微普法大赛活动 候选人 分页及搜索
     * @param
     * @return
     */
    List<ActivityBlueSkyPageInfo> getActivityBlueSkyPage(ActivityBlueSkyPageInfo entity);



}
