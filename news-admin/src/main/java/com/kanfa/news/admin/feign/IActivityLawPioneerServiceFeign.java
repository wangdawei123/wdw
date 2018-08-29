package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.ActivityLawPioneer;
import com.kanfa.news.admin.entity.VideoType;
import com.kanfa.news.admin.vo.activity.ActivityLawPioneerPageInfo;
import com.kanfa.news.admin.vo.activity.PioneerActivityPageInfo;
import com.kanfa.news.admin.vo.channel.ActivityLawPioneerInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/27 19:38
 */
@FeignClient(name = "service-provider-news")
public interface IActivityLawPioneerServiceFeign {
    /**
     * 新增活动
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/activityLawPioneer/getList", method = RequestMethod.POST)
    ObjectRestResponse<List<ActivityLawPioneerInfo>> getList(@RequestBody ActivityLawPioneerInfo entity);


    /**
     * 政法先锋-->机构或个人分页及查询
     * @param entity
     * @return
     */
    @RequestMapping(value = "/activityLawPioneer/getActivityLawPioneerPage",method =RequestMethod.POST )
    TableResultResponse<ActivityLawPioneerPageInfo> getPioneerActivityPage(ActivityLawPioneerPageInfo entity);


    @RequestMapping(value = "/activityLawPioneer/getMaxSort",method = RequestMethod.GET)
    Integer getMaxSort(@RequestParam("activityLawId")Integer activityLawId);


    //新增
    @RequestMapping(value = "/activityLawPioneer", method = RequestMethod.POST)
    ObjectRestResponse<ActivityLawPioneer> add(@RequestBody ActivityLawPioneer entity);

    //得到
    @RequestMapping(value = "/activityLawPioneer/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<ActivityLawPioneer> get(@PathVariable("id") int id);

    //编辑
    @RequestMapping(value = "/activityLawPioneer/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<ActivityLawPioneer> update(@PathVariable("id") Integer id, @RequestBody ActivityLawPioneer entity);

}
