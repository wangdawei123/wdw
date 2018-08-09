package com.kanfa.news.admin.feign;

/**
 * Created by Chen
 * on 2018/4/17 18:04
 */

import com.kanfa.news.admin.entity.ActivityBlueSkyComment;
import com.kanfa.news.admin.vo.activity.ActivityBlueSkyCommentPageInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "service-provider-news")
public interface IActivityBlueSkyCommentServiceFeign {


    /**
     * 青春普法
     * 候选人评论列表 的分页及按审核状态搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/activityBlueSkyComment/getActivityBlueSkyCommentPage", method = RequestMethod.POST)
    TableResultResponse<ActivityBlueSkyCommentPageInfo> getActivityBlueSkyCommentPage(@RequestBody ActivityBlueSkyCommentPageInfo entity);


    /**
     * 青春普法
     * 候选人评论 得到详情
     * @param
     * @return
     */
    @RequestMapping(value = "/activityBlueSkyComment/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<ActivityBlueSkyComment> get(@PathVariable("id") int id);


    /**
     * 青春普法
     * 候选人评论 编辑
     * @param
     * @return
     */
    @RequestMapping(value = "/activityBlueSkyComment/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<ActivityBlueSkyComment> update(@PathVariable("id") Integer id, @RequestBody ActivityBlueSkyComment entity);


}
