package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.entity.ActivityRaffle;
import com.kanfa.news.info.mapper.ActivityRaffleMapper;
import com.kanfa.news.info.vo.admin.activity.ActivityRafflePageInfo;
import com.kanfa.news.info.vo.admin.activity.VoteActivityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 20:02:37
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityRaffleBiz extends BaseBiz<ActivityRaffleMapper,ActivityRaffle> {


    @Autowired
    private ActivityRaffleMapper activityRaffleMapper;

    /**
     * 抽奖活动的分页及搜索
     * @return
     */
    public TableResultResponse<ActivityRafflePageInfo> getActivityRafflePage(ActivityRafflePageInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        entity.setIsDelete(0);
        List<ActivityRafflePageInfo> list = activityRaffleMapper.getActivityRafflePage(entity);
        return new TableResultResponse<ActivityRafflePageInfo>(result.getTotal(),list);
    }
}