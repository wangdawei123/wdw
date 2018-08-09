package com.kanfa.news.activity.rest;

import com.kanfa.news.activity.biz.ActivityBiz;
import com.kanfa.news.activity.biz.ActivityBlueSkyBiz;
import com.kanfa.news.activity.entity.Activity;
import com.kanfa.news.activity.entity.ActivityBlueSky;
import com.kanfa.news.activity.vo.info.ActivityBlueSkyInfo;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.activity.biz.UserActivityCouponBiz;
import com.kanfa.news.activity.entity.UserActivityCoupon;
import com.kanfa.news.common.util.DateUtil;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("userActivityCoupon")
public class UserActivityCouponController extends BaseController<UserActivityCouponBiz,UserActivityCoupon> {

    @Autowired
    private ActivityBiz activityBiz;
    @Autowired
    private ActivityBlueSkyBiz activityBlueSkyBiz;


    /**
     *  青春微普法活动  ---- 暂停，后延
     */
    @RequestMapping(value = "/userActivityCoupon/microvideoIndex", method = RequestMethod.POST)
    ObjectRestResponse microvideoIndex(@RequestParam("activity_id") Integer activity_id,
                                       @RequestParam("is_weixin") Integer is_weixin){
        Map<String ,Object> data = new HashMap<>(15);

       Activity activity = activityBiz.getActivity(activity_id , LiveCommonEnum.MICRO_ID);

        //获取总榜数据
        List<ActivityBlueSkyInfo> blueSkyDatas = activityBlueSkyBiz.getAllData(activity_id);

        //获取日榜数据
        List<ActivityBlueSkyInfo> blueSkyTopDatas = activityBlueSkyBiz.getDaydata(activity_id);

        //数据组合
        data.put("id" ,activity.getId());
        data.put("title" ,activity.getTitle());
        data.put("background" ,activity.getImage());
        data.put("start_time" ,activity.getStartTime());
        data.put("end_time" ,activity.getEndTime());
        SimpleDateFormat dfs = new SimpleDateFormat("MM\\dd");
        data.put("activity_time" ,dfs.format(activity.getStartTime().getTime() - activity.getEndTime().getTime()));
        data.put("day_rule" ,activity.getDayRule());
        data.put("all_rule" ,activity.getRule());
        data.put("day_data" ,setActivityData( activity_id ,blueSkyDatas ,0 ,0L ,0L ,is_weixin));
        data.put("all_data" ,setActivityData( activity_id ,blueSkyDatas ,1 ,activity.getStartTime().getTime() ,activity.getEndTime().getTime() ,is_weixin));

        return null;
    }

    /**
     * 处理数据是否打榜+url+top  ---- 暂停，后延
     * @param    datas
     * @param    isAll  是否是总榜数据
     * @param    isWeiXin  是否微信浏览器
     * @return   data
     */
    private List<Map<String ,Object>> setActivityData(Integer activityId ,
                                                      List<ActivityBlueSkyInfo> datas ,
                                                      @RequestParam(value = "isAll" ,defaultValue = "0") Integer isAll ,
                                                      @RequestParam(value = "startTime" ,defaultValue = "0")Long startTime ,
                                                      @RequestParam(value = "endTime" ,defaultValue = "0")Long endTime ,
                                                      Integer isWeiXin){
        SimpleDateFormat dfs = new SimpleDateFormat("YYMMdd");
        String nowDate = dfs.format(new Date());
        String startTimes = dfs.format(startTime);
        String endTimes = dfs.format(endTime);
        if(datas.size() == 0){
            return null;
        }




        return null;
    }



}