package com.kanfa.news.activity.biz;

import com.kanfa.news.activity.entity.ActivityBlueSkyComment;
import com.kanfa.news.activity.mapper.ActivityBlueSkyCommentMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-17 11:23:12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityBlueSkyCommentBiz extends BaseBiz<ActivityBlueSkyCommentMapper,ActivityBlueSkyComment> {


    @Autowired
    private ActivityBlueSkyCommentMapper activityBlueSkyCommentMapper;

}