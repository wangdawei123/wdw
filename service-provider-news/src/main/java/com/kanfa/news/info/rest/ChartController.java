package com.kanfa.news.info.rest;

import com.alibaba.fastjson.JSONArray;
import com.kanfa.news.info.biz.ChartBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/30 16:02
 */
@RestController
@RequestMapping("chart")
public class ChartController {

    @Autowired
    private ChartBiz chartBiz;



    private static final Integer KEYSLENGTH = 6;
    /**返回数据Map的长度*/
    private static final Integer DATALENGTH = 7;

    /**日统计、月统计 Map的长度*/
    private static final Integer DAYMONTHMAPLENGTH = 3;
    /** top排行榜长度*/
    private static final Integer TOPLENGTH = 1;

    @RequestMapping("/getData")
    public Map<String,Object> getData(@RequestParam("days")Integer days){
        Map<String,Object> map = new HashMap<String,Object>(DATALENGTH);
        Map<String,Object> dayCountMap = new HashMap<String,Object>(DAYMONTHMAPLENGTH);
        dayCountMap.put("data",getResult());
//        dayCountMap.put("date",getHour());
        dayCountMap.put("pressTop",getPressTop());
        map.put("dayCount",dayCountMap);

        Map<String,Object> mongthCountMap = new HashMap<String,Object>(DAYMONTHMAPLENGTH);
        mongthCountMap.put("data",getMonthChartData(days));
        mongthCountMap.put("liveCount",getLiveCount());
        map.put("monthCount",mongthCountMap);

        Map<String,Object> articleTopMap = new HashMap<String,Object>(TOPLENGTH);
        articleTopMap.put("data",getArticleTop());
        map.put("articleTop",articleTopMap);

        Map<String,Object> videoTopMap = new HashMap<String,Object>(TOPLENGTH);
        videoTopMap.put("data",getVideoTop());
        map.put("videoTop",videoTopMap);

        Map<String,Object> liveTopMap = new HashMap<String,Object>(TOPLENGTH);
        liveTopMap.put("data",getLiveTop());
        map.put("liveTopMap",liveTopMap);

        return map;
    }

    private List<JSONArray>  getLiveCount() {
        return chartBiz.getLiveCount();
    }


    /**
     * 获取日统计数据
     * @return
     */
    public Map<String,Object> getResult(){
        List<String> keys = new ArrayList<String>(KEYSLENGTH);
        keys.add("pv");
        keys.add("play");
        keys.add("new_oiginal");
        keys.add("new_no_oiginal");
        keys.add("video_oiginal");
        keys.add("video_no_oiginal");
        return chartBiz.getChartData(keys);
    }

    /**
     * 获取日统计时间段
     * @return
     */
   /* public List<String> getHour(){
        return chartBiz.getHour();
    }*/

    /**
     * 发稿量Top榜
     * @return
     */
    public Map<String,Object> getPressTop(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String startDate = df.format(date)+" 00:00:00";
        String endDate = df.format(date)+" 23:59:59";
        return chartBiz.getTopPress(startDate,endDate);
    }

    public Map<String,Object> getMonthChartData(Integer days){

        return chartBiz.getMonthChartData(days);
    }

    public Map<String,Object> getArticleTop(){
        return chartBiz.getArticleOrVideoTop(1);
    }

    public Map<String,Object> getVideoTop(){
        return chartBiz.getArticleOrVideoTop(2);
    }

   public Map<String,Object> getLiveTop(){
        return chartBiz.getArticleOrVideoTop(3);
    }

}
