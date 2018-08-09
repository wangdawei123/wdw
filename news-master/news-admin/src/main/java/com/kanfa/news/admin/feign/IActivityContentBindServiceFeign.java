package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.ActivityContentBind;
import com.kanfa.news.admin.entity.Content;
import com.kanfa.news.admin.entity.VideoType;
import com.kanfa.news.admin.vo.activity.ActivityContentBindPageInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/4/18 14:44
 */
@FeignClient(name = "service-provider-news")
public interface IActivityContentBindServiceFeign {

    /**
     * 政法先锋 关联内容
     * @param
     * @return
     */
    @RequestMapping(value = "/activityContentBind/getActivityContentBindPage",method = RequestMethod.POST )
    TableResultResponse<ActivityContentBindPageInfo> getActivityContentBindPage(ActivityContentBindPageInfo entity);


    /**
     * 政法先锋 关联内容 按标题搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/activityContentBind/getSearchPage",method = RequestMethod.GET)
    TableResultResponse<Content> getSearchPage(@RequestParam(name = "page") Integer page,
                                               @RequestParam(name = "limit") Integer limit,
                                               @RequestParam(name = "activityId") Integer activityId,
                                               @RequestParam(name = "title") String title);



    /**
     * 政法先锋 关联内容 关联
     * @param
     * @return
     */
    @RequestMapping(value = "/activityContentBind", method = RequestMethod.POST)
    ObjectRestResponse<ActivityContentBind> add(@RequestBody ActivityContentBind entity);

    @RequestMapping(value = "/activityContentBind/getMaxOrderNum",method = RequestMethod.GET)
    Integer getMaxOrderNum(@RequestParam(name = "activityId") Integer activityId);


    /**
     * 政法先锋 关联内容 解除关联
     * @param
     * @return
     */
    @RequestMapping(value = "/activityContentBind/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<ActivityContentBind> update(@PathVariable("id") Integer id, @RequestBody ActivityContentBind entity);

}
