package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.ActivityVotePeople;
import com.kanfa.news.admin.vo.activity.ActivityVotePeopleAddInfo;
import com.kanfa.news.admin.vo.activity.ActivityVotePeoplePageInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/4/12 11:44
 */
@FeignClient(name = "service-provider-news")
public interface IActivityVotePeopleServiceFeign {

    /**
     * 建言/投票活动中的候选人分页信息及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/activityVotePeople/getVoteActivityPeoplePage",method = RequestMethod.POST)
    TableResultResponse<ActivityVotePeoplePageInfo> getVoteActivityPeoplePage(@RequestBody ActivityVotePeoplePageInfo entity);

    /**
     * 建言/投票活动中的候选人 新增
     * @param
     * @return
     */
    @RequestMapping(value = "/activityVotePeople/voteActivityPeopleAdd",method = RequestMethod.POST)
    ObjectRestResponse  voteActivityPeopleAdd(@RequestBody ActivityVotePeopleAddInfo entity);

    /**
     * 得到 建言/投票活动中的候选人信息
     * @param
     * @return
     */
    @RequestMapping(value = "/activityVotePeople/getOneActivityVotePeople",method = RequestMethod.GET)
    ActivityVotePeopleAddInfo getOneActivityVotePeople(@RequestParam("id")Integer id);

    /**
     * 编辑 建言/投票活动中的候选人信息
     * @param
     * @return
     */
    @RequestMapping(value = "/activityVotePeople/updateActivityVotePeople",method = RequestMethod.POST)
    ObjectRestResponse  updateActivityVotePeople(@RequestBody ActivityVotePeopleAddInfo entity);

    /**
     * 编辑 建言/投票活动中的候选人信息(只更新单表)
     * @param
     * @return
     */
    @RequestMapping(value = "/activityVotePeople/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<ActivityVotePeople> update(@PathVariable("id") Integer id, @RequestBody ActivityVotePeople entity);
}
