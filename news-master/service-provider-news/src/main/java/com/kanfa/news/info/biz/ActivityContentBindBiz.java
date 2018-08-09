package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.entity.Content;
import com.kanfa.news.info.vo.admin.activity.ActivityContentBindPageInfo;
import com.kanfa.news.info.vo.admin.activity.PioneerActivityPageInfo;
import com.kanfa.news.info.vo.admin.video.ContentBroadcastBindInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.ActivityContentBind;
import com.kanfa.news.info.mapper.ActivityContentBindMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-18 14:17:51
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityContentBindBiz extends BaseBiz<ActivityContentBindMapper,ActivityContentBind> {

    @Autowired
    private ActivityContentBindMapper activityContentBindMapper;


    /**
     * 政法先锋 关联内容
     * @param
     * @return
     */
    public TableResultResponse<ActivityContentBindPageInfo> getActivityContentBindPage(ActivityContentBindPageInfo entity){
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //政法先锋
        entity.setIsDelete(0);
        List<ActivityContentBindPageInfo> pioneerActivityPage = activityContentBindMapper.getActivityContentBindPage(entity);
        return new TableResultResponse<ActivityContentBindPageInfo>(result.getTotal(),pioneerActivityPage);
    }

    /**
     * 政法先锋 关联内容 的 按内容标题搜索
     * @param
     * @return
     */
    public TableResultResponse<Content> getSearchPage(Query query,
                                                      Integer activityId,
                                                      String title){
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Content> searchlist = activityContentBindMapper.getSearchPage(activityId,title);
        return new TableResultResponse<Content>(result.getTotal(), searchlist);
    }

    public Integer getMaxOrderNum(Integer activityId){
        Integer maxOrderNum = activityContentBindMapper.getMaxOrderNum(activityId);
        return maxOrderNum;
    }


}