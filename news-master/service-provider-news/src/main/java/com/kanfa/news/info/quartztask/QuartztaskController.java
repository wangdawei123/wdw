package com.kanfa.news.info.quartztask;

import com.kanfa.news.info.biz.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/12 18:05
 */
@RestController
@RequestMapping("quartz")
public class QuartztaskController {

    @Autowired
    private CountContentViewBiz countContentViewBiz;

    @Autowired
    private WeixinBiz weixinBiz;

    @Autowired
    private HotGetListBiz hotGetListBiz;

    @Autowired
    private RecommendweightBiz recommendweightBiz;

    @Autowired
    private VideokpiBiz videokpiBiz;

    @Autowired
    private ChartBiz chartBiz;

    @Autowired
    private KpiCountUserContentBiz kpiCountUserContentBiz;

    @Autowired
    private ContentKpiCountBiz contentKpiCountBiz;

    /**
     * 每日文章 视频 图集等统计
     */
    @GetMapping("/countContentView")
    public void getCountContentView() {
        countContentViewBiz.getCountContentView();
    }

    /**
     * 每日渠道发稿量统计
     */
    @GetMapping("/countChannelContent")
    public void getCountChannelContent() {
        countContentViewBiz.getCountChannelContent();
    }

    /**
     * 每日类目文章统计
     */
    @GetMapping("/countCate")
    public void getCountCate() {
        countContentViewBiz.getCountCate();
    }

    /**
     * 微信定时脚本   每隔1个小时去执行一次
     */
    @GetMapping("/getSignPackage")
    public void getSignPackage() {
        weixinBiz.getSignPackage();
    }

    /**
     * pc端的热门数据
     */
    @GetMapping("/setCache")
    public void setCatche() {
        hotGetListBiz.setCatche();
    }

    /**
     * 推荐权重任务
     * 查询更新时间为4小时之前 5小时之内的文章进行降权
     * 开始任务
     */
    @GetMapping("/recommendWeight/start")
    public void recommendweightStart() {
        recommendweightBiz.start();
    }

    /**
     * 推荐权重任务
     * 查询更新时间为4小时之前 5小时之内的文章进行降权
     * 修改直播权重方法
     */
    @GetMapping("/recommendWeight/live")
    public void recommendweightLive() {
        recommendweightBiz.live();
    }

    /**
     * 视频每日统计
     */
    @GetMapping("/videokpi/start")
    public void videokpiStart() {
        videokpiBiz.start();
    }

    /*更新mongo数据 -- content_detail_info*/
    @GetMapping("/chartsRedis/redisToMongo")
    public void redisToMongo() {
        chartBiz.redisToMongo();
    }

    /*图表--获取每小时数据*/
    @GetMapping("/chartsRedis/getHoursData")
    public void getHoursData() {
        chartBiz.getHoursData();
    }

    /*清除前一天每小时获得的数据*/
    @GetMapping("/chartsRedis/delRedisKey")
    public void delRedisKey() {
        chartBiz.delRedisKey();
    }

    /*月统计数据--每天凌晨执行*/
    @GetMapping("/dayChartsRedis/redisToMongo")
    public void dayChartsRedisToMongo() {
        chartBiz.dayChartsRedisToMongo();
    }

    /*图表查询- 每天统计pv or 播放量*/
    @GetMapping("/chartsMongo/dayPvPlay")
    public void dayPvPlay() {
        chartBiz.dayPvPlay();
    }

    /*同步content_detail_info pv/play*/
    @GetMapping("/chartsMongo/setPvViews")
    public void setPvViews() {
        chartBiz.setPvViews();
    }

    /*人员发布数据生成*/
    @GetMapping("/countUserContent/dataCreate")
    public void insertCountUserContent(@RequestParam(required = false) String date) {
            kpiCountUserContentBiz.saveCountUserContent(date);
    }

    @GetMapping("/contentKpiCount/dataCreate")
    public void insertContentKpiCount(@RequestParam(required = false) String date,
                                    @RequestParam(required = false) Integer contentId,
                                    @RequestParam(required = false) Boolean isUpdate
                                    ){
        contentKpiCountBiz.countContent(date,contentId,isUpdate);
    }


}
