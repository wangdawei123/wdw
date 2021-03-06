package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.Feedback;
import com.kanfa.news.app.mapper.FeedbackMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 意见反馈表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-24 15:48:03
 */
@Service
public class FeedbackBiz extends BaseBiz<FeedbackMapper,Feedback> {

    @Autowired
    private FeedbackMapper feedbackMapper;

    public Integer insertMessage(Feedback feedback){
        int i = feedbackMapper.insertSelective(feedback);
        System.out.println(i);
        return i;
    }
}