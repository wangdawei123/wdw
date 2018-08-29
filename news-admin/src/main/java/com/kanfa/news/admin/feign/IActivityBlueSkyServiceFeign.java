package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.ActivityBlueSky;
import com.kanfa.news.admin.vo.activity.ActivityBlueSkyAddInfo;
import com.kanfa.news.admin.vo.activity.ActivityBlueSkyPageInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/4/17 14:25
 */
@FeignClient(name = "service-provider-news")
public interface IActivityBlueSkyServiceFeign {

    /**
     *  新增 青春微普法大赛的候选人
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/activityBlueSky/insertActivityBlueSky",method = RequestMethod.POST)
    ObjectRestResponse insertActivityBlueSky(@RequestBody ActivityBlueSkyAddInfo entity);

    /**
     *  得到 青春微普法大赛的候选人
     *
     * @param id
     * @return ActivityBlueSkyAddInfo
     */
    @RequestMapping(value = "/activityBlueSky/getOneActivityBlueSky",method = RequestMethod.GET)
    ActivityBlueSkyAddInfo getOneActivityBlueSky(@RequestParam("id")Integer id);


    /**
     *  编辑 青春微普法大赛的候选人
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/activityBlueSky/updateActivityBlueSky",method = RequestMethod.POST)
    ObjectRestResponse updateActivityBlueSky(@RequestBody ActivityBlueSkyAddInfo entity);


    /**
     *  删除 青春微普法大赛的候选人(kf_activity_blue_sky 单表使用)
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/activityBlueSky/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<ActivityBlueSky> update(@PathVariable("id") Integer id, @RequestBody ActivityBlueSky entity);



    /**
     * 青春微普法大赛的候选人 分页及搜索
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/activityBlueSky/getActivityBlueSkyPage",method = RequestMethod.POST)
    TableResultResponse<ActivityBlueSkyPageInfo> getActivityBlueSkyPage(@RequestBody ActivityBlueSkyPageInfo entity);





}
