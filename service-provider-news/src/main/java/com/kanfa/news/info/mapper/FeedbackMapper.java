package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.Feedback;
import com.kanfa.news.info.vo.admin.appuser.FeedbackInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 意见反馈表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-06-05 18:30:42
 */
public interface FeedbackMapper extends Mapper<Feedback> {

    List<FeedbackInfo> getFeedbackPage();
}
