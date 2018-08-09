package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.ActiveAwardResult;
import com.kanfa.news.app.mapper.ActiveAwardResultMapper;
import com.kanfa.news.app.vo.admin.activity.ActiveAwardResultInfo;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-19 10:48:54
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActiveAwardResultBiz extends BaseBiz<ActiveAwardResultMapper,ActiveAwardResult> {

    @Autowired
    private ActiveAwardResultMapper activeAwardResultMapper;

    public List<ActiveAwardResultInfo> getActiveAwardResult(Integer activityRaffleId){
        return activeAwardResultMapper.getActiveAwardResult(activityRaffleId);
    }


}