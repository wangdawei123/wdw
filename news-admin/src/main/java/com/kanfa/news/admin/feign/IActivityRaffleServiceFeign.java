package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.ActivityRaffle;
import com.kanfa.news.admin.vo.activity.ActivityRafflePageInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Jezy
 */
@FeignClient(name = "service-provider-news")
public interface IActivityRaffleServiceFeign {
    /**
     * 新增抽奖活动
     * @param entity
     * @return
     */
    @RequestMapping(value = "/activityRaffle", method = RequestMethod.POST)
    ObjectRestResponse<ActivityRaffle> add(@RequestBody ActivityRaffle entity);

    /**
     * 得到 抽奖活动详情
     * @param
     * @return
     */
    @RequestMapping(value = "/activityRaffle/{id}", method = RequestMethod.GET)
    ObjectRestResponse<ActivityRaffle> get(@PathVariable("id") int id);


    /**
     * 编辑 抽奖活动
     * @param
     * @return
     */
    @RequestMapping(value = "/activityRaffle/{id}", method = RequestMethod.PUT)
    ObjectRestResponse<ActivityRaffle> update(@PathVariable("id") Integer id, @RequestBody ActivityRaffle entity);


    /**
     * 抽奖活动的分页及搜索
     * @return
     */
    @RequestMapping(value = "/activityRaffle/getActivityRafflePage",method =RequestMethod.POST )
    TableResultResponse<ActivityRafflePageInfo> getPioneerActivityPage(ActivityRafflePageInfo entity);

}
