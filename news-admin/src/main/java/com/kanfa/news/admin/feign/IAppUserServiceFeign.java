package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.vo.activity.VoteActivityAddInfo;
import com.kanfa.news.admin.vo.appuser.AppUserInfo;
import com.kanfa.news.admin.vo.appuser.FeedbackInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/4/11 11:04
 */

@FeignClient(name = "service-provider-news")
public interface IAppUserServiceFeign {

    /**
     * 查询前端用户分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/appUser/getPage",method = RequestMethod.GET)
    TableResultResponse<AppUserInfo> getPage(@RequestParam Map<String, Object> params);

    /**
     * 查询单个
     * @param id
     * @return
     */
    @RequestMapping(value = "/appUser/getAppUser/{id}",method = RequestMethod.GET)
    ObjectRestResponse<AppUserInfo> getAppUser(@PathVariable("id") Integer id);

    /**
     * 统计
     * @return
     */
    @RequestMapping(value = "/appUser/countAppUser",method = RequestMethod.GET)
    ObjectRestResponse<Map<String,Integer>> countAppUser();

    /**
     * 屏蔽与恢复
     * @param id
     * @param appUserInfo
     * @return
     */
    @RequestMapping(value = "/appUser/{id}",method = RequestMethod.PUT)
    ObjectRestResponse<AppUserInfo> blockAppUser(@PathVariable("id") Integer id,@RequestBody AppUserInfo appUserInfo);

    /**
     * 反馈分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/feedback/getPage",method = RequestMethod.GET)
    TableResultResponse<FeedbackInfo> getFeedbackPage(@RequestParam Map<String, Object> params);

    /**
     * 批量删除
     * @param params
     * @return
     */
    @RequestMapping(value = "/feedback/batchDeleteFeedback",method = RequestMethod.POST)
    ObjectRestResponse<Integer> batchDeleteFeedback(@RequestBody Map<String, List<Integer>> params);

    /**
     * 删除单个
     * @param id
     * @return
     */
    @RequestMapping(value = "/feedback/{id}",method = RequestMethod.DELETE)
    ObjectRestResponse<FeedbackInfo> deleteFeedback(@PathVariable("id") Integer id);

}
