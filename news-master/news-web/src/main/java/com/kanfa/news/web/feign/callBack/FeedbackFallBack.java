package com.kanfa.news.web.feign.callBack;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.entity.Feedback;
import com.kanfa.news.web.feign.IFeedbackFeign;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by kanfa on 2018/3/27.
 * @Author ffy
 */
@Service
public class FeedbackFallBack implements IFeedbackFeign{
    @Override
    public ObjectRestResponse insertMessage(Integer id, String advice, String phone){
        return null;
    }
}
