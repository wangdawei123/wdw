package com.kanfa.news.quartz.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/22 16:17
 */
@FeignClient(name = "service-provider-news")
public interface TaskJobFeign {

    /**
     * 每日文章 视频 图集等统计
     */
    @GetMapping("/quartz/countContentView")
    void getCountContentView();

    /**
     * 每日渠道发稿量统计
     */
    @GetMapping("/quartz/countChannelContent")
    void getCountChannelContent();

    /**
     * 每日类目文章统计
     */
    @GetMapping("/quartz/countCate")
    void getCountCate();

    /**
     *  微信定时脚本   每隔1个小时去执行一次
     */
    @GetMapping("/quartz/getSignPackage")
    void getSignPackage();

    /**
     * pc端的热门数据
     */
    @GetMapping("/quartz/setCache")
    void setCatche();

    /**
     * 推荐权重任务
     * 查询更新时间为4小时之前 5小时之内的文章进行降权
     * 开始任务
     */
    @GetMapping("/quartz/recommendWeight/start")
    void recommendweightStart();

    /**
     * 推荐权重任务
     * 查询更新时间为4小时之前 5小时之内的文章进行降权
     * 修改直播权重方法
     */
    @GetMapping("/quartz/recommendWeight/live")
    void recommendweightLive();

    /**
     * 视频每日统计
     */
    @GetMapping("/quartz/videokpi/start")
    void videokpiStart();

    /*更新mongo数据 -- content_detail_info*/
    @GetMapping("/quartz/chartsRedis/redisToMongo")
    void redisToMongo();

    /*图表--获取每小时数据*/
    @GetMapping("/quartz/chartsRedis/getHoursData")
    void getHoursData();

    /*清除前一天每小时获得的数据*/
    @GetMapping("/quartz/chartsRedis/delRedisKey")
    void delRedisKey();

    /*月统计数据--每天凌晨执行*/
    @GetMapping("/quartz/dayChartsRedis/redisToMongo")
    void dayChartsRedisToMongo();

    /*图表查询- 每天统计pv or 播放量*/
    @GetMapping("/quartz/chartsMongo/dayPvPlay")
    void dayPvPlay();

    /*同步content_detail_info pv/play*/
    @GetMapping("/quartz/chartsMongo/setPvViews")
    void setPvViews();

    /*人员发布数据生成*/
    @GetMapping("/quartz/countUserContent/dataCreate")
    void insertCountUserContent(@RequestParam(required = false,value = "date")String date);

    @GetMapping("/quartz/contentKpiCount/dataCreate")
    void insertContentKpiCount(@RequestParam(required = false,value = "date") String date,
                                      @RequestParam(required = false,value = "contentId") Integer contentId,
                                      @RequestParam(required = false,value = "isUpdate") Boolean isUpdate);
}
