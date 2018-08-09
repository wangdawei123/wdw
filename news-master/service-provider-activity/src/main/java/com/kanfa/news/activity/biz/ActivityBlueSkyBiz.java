package com.kanfa.news.activity.biz;

import com.kanfa.news.activity.vo.info.ActivityBlueSkyInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.activity.entity.ActivityBlueSky;
import com.kanfa.news.activity.mapper.ActivityBlueSkyImageMapper;
import com.kanfa.news.activity.mapper.ActivityBlueSkyMapper;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-17 11:23:12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityBlueSkyBiz extends BaseBiz<ActivityBlueSkyMapper,ActivityBlueSky> {

    @Autowired
    private ActivityBlueSkyMapper activityBlueSkyMapper;
    @Autowired
    private ActivityBlueSkyImageMapper activityBlueSkyImageMapper;

    public List<ActivityBlueSkyInfo> getAllData(Integer id){
        return activityBlueSkyMapper.selectById(id);
    }

    public List<ActivityBlueSkyInfo> getDaydata(Integer id){
        return activityBlueSkyMapper.selectABSById(id);
    }

}