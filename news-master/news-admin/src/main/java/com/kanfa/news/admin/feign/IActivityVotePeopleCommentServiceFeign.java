package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.ActivityVotePeopleComment;
import com.kanfa.news.admin.vo.activity.ActivityVotePeopleCommentPageInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/4/13 14:27
 */
@FeignClient(name = "service-provider-news")
public interface IActivityVotePeopleCommentServiceFeign {



    /**
     * 候选人评论列表 的分页及按审核状态搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/activityVotePeopleComment/getActivityVotePeopleCommentPage", method = RequestMethod.POST)
    TableResultResponse<ActivityVotePeopleCommentPageInfo> getActivityVotePeopleCommentPage(@RequestBody ActivityVotePeopleCommentPageInfo entity);


    /**
     * 得到候选人评论列表详情
     * @param
     * @return
     */
    @RequestMapping(value = "/activityVotePeopleComment/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<ActivityVotePeopleComment> get(@PathVariable("id") int id);

    /**
     * 编辑 候选人评论列表
     * @param
     * @return
     */
    @RequestMapping(value = "/activityVotePeopleComment/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<ActivityVotePeopleComment> update(@PathVariable("id") Integer id, @RequestBody ActivityVotePeopleComment entity);


}
