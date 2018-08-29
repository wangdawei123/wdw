package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ActivityVote;
import tk.mybatis.mapper.common.Mapper;

/**
 * 活动--投票表
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-23 11:33:20
 */
public interface ActivityVoteMapper extends Mapper<ActivityVote> {

    int insertActivityVote(ActivityVote entity);

	
}
