package com.kanfa.news.activity.biz;

import org.springframework.stereotype.Service;

import com.kanfa.news.activity.entity.ActivityVote;
import com.kanfa.news.activity.mapper.ActivityVoteMapper;
import com.kanfa.news.common.biz.BaseBiz;

/**
 * 活动--投票表
 *
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-23 11:33:20
 */
@Service
public class ActivityVoteBiz extends BaseBiz<ActivityVoteMapper,ActivityVote> {
}