package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.ActivityContentBindBiz;
import com.kanfa.news.info.entity.ActivityContentBind;
import com.kanfa.news.info.entity.Content;
import com.kanfa.news.info.vo.admin.activity.ActivityContentBindPageInfo;
import com.kanfa.news.info.vo.admin.activity.PioneerActivityPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("activityContentBind")
public class ActivityContentBindController extends BaseController<ActivityContentBindBiz,ActivityContentBind> {


    @Autowired
    private ActivityContentBindBiz activityContentBindBiz;


    /**
     * 政法先锋 关联内容
     * @param
     * @return
     */
    @RequestMapping(value = "/getActivityContentBindPage", method = RequestMethod.POST)
    public TableResultResponse<ActivityContentBindPageInfo> getActivityContentBindPage(@RequestBody ActivityContentBindPageInfo entity) {
        return activityContentBindBiz.getActivityContentBindPage(entity);
    }

    /**
     * 政法先锋 关联内容 的 按内容标题搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getSearchPage",method = RequestMethod.GET)
    public TableResultResponse<Content> getSearchPage(@RequestParam(name = "page") Integer page,
                                                      @RequestParam(name = "limit") Integer limit,
                                                      @RequestParam(name = "activityId") Integer activityId,
                                                      @RequestParam(name = "title") String title){
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return activityContentBindBiz.getSearchPage(query,activityId,title);
    }

    @RequestMapping(value = "/getMaxOrderNum",method = RequestMethod.GET)
    public Integer getMaxOrderNum(@RequestParam(name = "activityId") Integer activityId){
        return activityContentBindBiz.getMaxOrderNum(activityId);
    }



}