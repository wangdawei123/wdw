package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ActivityRaffleBiz;
import com.kanfa.news.info.entity.ActivityRaffle;
import com.kanfa.news.info.vo.admin.activity.ActivityRafflePageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activityRaffle")
public class ActivityRaffleController extends BaseController<ActivityRaffleBiz,ActivityRaffle> {


    @Autowired
    private ActivityRaffleBiz activityRaffleBiz;

    /**
     * 抽奖活动的分页及搜索
     * @return
     */
    @RequestMapping(value = "/getActivityRafflePage", method = RequestMethod.POST)
    public TableResultResponse<ActivityRafflePageInfo> getActivityRafflePage(@RequestBody ActivityRafflePageInfo entity) {
        return activityRaffleBiz.getActivityRafflePage(entity);
    }

}