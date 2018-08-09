package com.kanfa.news.activity.biz;

import com.kanfa.news.activity.entity.Activity;
import com.kanfa.news.activity.mapper.ActivityMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 活动表
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-10 17:01:20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityBiz extends BaseBiz<ActivityMapper,Activity> {

    @Autowired
    private ActivityMapper activityMapper;

    public Activity getActivity(Integer activityId ,Integer type){
        Activity a = new Activity();
        a.setId(activityId);
        a.setType(type);
        return activityMapper.selectOne(a);
    }


}