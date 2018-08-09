package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ActivityVotePeopleImage;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 活动--投票--投票人头像信息表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-12 16:01:30
 */
public interface ActivityVotePeopleImageMapper extends Mapper<ActivityVotePeopleImage> {


    //得到动--投票--投票人头像信息表 sort的最大值
    Integer selectMaxSortById(@Param("activityVotePeopleId") Integer activityVotePeopleId);

    //得到投票人头像信息表 image的集合按sort排序
    List<String> getImageList(@Param("activityVotePeopleId") Integer activityVotePeopleId);
	
}
