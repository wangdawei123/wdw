package com.kanfa.news.activity.biz;

import com.kanfa.news.activity.entity.ActiveAwardResult;
import com.kanfa.news.activity.mapper.ActiveAwardResultMapper;
import com.kanfa.news.activity.vo.info.ActivityAwardResultInfo;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-20 15:34:40
 */
@Service
public class ActiveAwardResultBiz extends BaseBiz<ActiveAwardResultMapper,ActiveAwardResult> {

    @Autowired
    private ActiveAwardResultMapper activeAwardResultMapper;

    public List<ActivityAwardResultInfo> getMessage(Integer uid){
        return activeAwardResultMapper.getMessage(uid);
    }


}