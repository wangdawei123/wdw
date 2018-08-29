package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.Activity;
import com.kanfa.news.admin.vo.activity.*;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/4/11 11:04
 */

@FeignClient(name = "service-provider-news")
public interface IActivityServiceFeign {

    /**
     * 新增建言/投票活动
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/activity/insertVoteActivity",method = RequestMethod.POST)
    ObjectRestResponse insertVoteActivity(VoteActivityAddInfo entity);

    /**
     * 得到新增建言/投票活动详情
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/activity/getOneVoteActivity",method = RequestMethod.GET)
    VoteActivityAddInfo getOneVoteActivity(@RequestParam("id")Integer id);

    /**
     * 编辑建言/投票活动
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/activity/updateVoteActivity",method = RequestMethod.POST)
    ObjectRestResponse updateVoteActivity(VoteActivityAddInfo entity);

    /**
     * 建言/投票活动分页及搜索
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/activity/getVoteActivityPage",method = RequestMethod.POST)
    TableResultResponse<VoteActivityInfo>  getVoteActivityPage(@RequestBody VoteActivityInfo entity);


    /**
     * 建言/投票活动(活动皆可用) 删除
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<Activity> update(@PathVariable("id") Integer id, @RequestBody Activity entity);

    /**
     * 优惠券活动(单表活动皆可用) 新增
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/activity", method = RequestMethod.POST)
    ObjectRestResponse<Activity> add(@RequestBody Activity entity);


    /**
     * 建言/投票活动分页及搜索
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/activity/getCouponsActivityPage",method = RequestMethod.POST)
    TableResultResponse<CouponsActivityPageInfo>  getCouponsActivityPage(@RequestBody CouponsActivityPageInfo entity);


    /**
     * 优惠券活动(单表活动皆可用) 得到详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<Activity> get(@PathVariable("id") int id);


    /**
     * 新增 青春微普法大赛活动
     * @param
     * @return
     */
    @RequestMapping(value = "/activity/addYouthActivity",method = RequestMethod.POST)
    ObjectRestResponse addYouthActivity(YouthActivityAddInfo entity);

    /**
     * 青春微普法大赛活动 得到详情
     * @param
     * @return
     */
    @RequestMapping(value = "/activity/getOneYouthActivity",method = RequestMethod.GET)
    YouthActivityAddInfo getOneYouthActivity(@RequestParam("id")Integer id);

    /**
     * 青春微普法大赛活动 编辑
     * @param
     * @return
     */
    @RequestMapping(value = "/activity/updateYouthActivity",method = RequestMethod.POST)
    ObjectRestResponse updateYouthActivity(YouthActivityAddInfo entity);


    /**
     * 青春微普法大赛活动 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/activity/getYouthActivityPage",method = RequestMethod.POST)
    TableResultResponse<YouthActivityPageInfo>  getYouthActivityPage(@RequestBody YouthActivityPageInfo entity);


    /**
     * 政法先锋活动 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/activity/getPioneerActivityPage",method =RequestMethod.POST )
    TableResultResponse<PioneerActivityPageInfo> getPioneerActivityPage(PioneerActivityPageInfo entity);

}
