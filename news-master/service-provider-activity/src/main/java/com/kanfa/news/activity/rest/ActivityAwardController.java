package com.kanfa.news.activity.rest;

import com.kanfa.news.activity.biz.ActiveAwardResultBiz;
import com.kanfa.news.activity.biz.ActivityAwardBiz;
import com.kanfa.news.activity.biz.ActivityLawPioneerBiz;
import com.kanfa.news.activity.biz.UserActivityCouponBiz;
import com.kanfa.news.activity.entity.ActivityAward;
import com.kanfa.news.activity.entity.ActivityLawPioneer;
import com.kanfa.news.activity.vo.info.ActivityAwardResultInfo;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.rest.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("activityAward")
public class ActivityAwardController extends BaseController<ActivityAwardBiz,ActivityAward> {
    @Autowired
    private ActiveAwardResultBiz activeAwardResultBiz;

    @Autowired
    private ActivityLawPioneerBiz activityLawPioneerBiz;

    @Autowired
    private UserActivityCouponBiz userActivityCouponBiz;

    /**
     *  app - 抽奖消息列表接口
     */
    @RequestMapping(value = "/getMessage", method = RequestMethod.POST)
    public AppObjectResponse getMessage(@RequestParam(value = "uid") Integer uid ) {
        AppObjectResponse response = new AppObjectResponse();
        List<ActivityAwardResultInfo> awards = activeAwardResultBiz.getMessage(uid);
        Map<String , Object> newmap = new HashMap<>(7);

        Map<Integer , String> award = new HashMap<>(7);
        award.put(3 ,"优酷90天会员" );
        award.put(4 ,"味多美代金券" );
        award.put(5 , "爱奇艺7天会员");
        List<Map<String , String>> list = new ArrayList<>();
        for(ActivityAwardResultInfo resultInfo : awards){
            Map<String , String> map = new HashMap<>(7);
            if(resultInfo.getPrizeId().equals(LiveCommonEnum.AWARD_LEVEL_APECIAL) ||resultInfo.getPrizeId().equals(LiveCommonEnum.AWARD_LEVEL_ONE) ){
                map.put("message" , "厉害了~真是运气爆棚有没有！恭喜您获得" + resultInfo.getPrizeName() + "奖励。");
            }else{
                String s = award.get(resultInfo.getPrizeType());
                map.put("message" , "厉害了~真是运气爆棚有没有！恭喜您获得" + s + "奖励。");
                map.put("code" ,resultInfo.getPrizeName()+"");
            }
            map.put("title" ,"活动消息！");
            SimpleDateFormat dfs = new SimpleDateFormat("MM月dd日");
            map.put("time" ,dfs.format(resultInfo.getPrizeTime()));
            list.add(map);
        }
        newmap.put("list",list);
        response.setData(newmap);
        return response;
    }



    /**
     *  app -青春微普法活动--首页接口
     */
    @RequestMapping(value = "/getLawList", method = RequestMethod.POST)
    public AppObjectResponse getLawList(@RequestParam(value = "page") Integer page ,
                                        @RequestParam(value = "pcount") Integer pcount) {
        AppObjectResponse response = new AppObjectResponse();
        Map<String ,Object> newMap = new HashMap<>(5);

        //机构或个人列表
        List<Map<String ,Object>> activityinfoList = new ArrayList<>();
        List<ActivityLawPioneer> list = activityLawPioneerBiz.getLawList();
        for(ActivityLawPioneer lawPioneer : list){
            Map<String ,Object> map = new HashMap<>(5);
            map.put("iconid",lawPioneer.getId());
            map.put("iconname",lawPioneer.getTitle());
            map.put("icon",lawPioneer.getIcon());
            activityinfoList.add(map);
        }

        Map<String ,Object> shareMap = new HashMap<>(5);
        shareMap.put("title", LiveCommonEnum.ACTIVITY_LAW_TITLE);
        shareMap.put("desc", LiveCommonEnum.ACTIVITY_LAW_DESC);
        shareMap.put("icon", LiveCommonEnum.ACTIVITY_LAW_ICON);
        shareMap.put("url", LiveCommonEnum.ACTIVITY_LAW_URL);

        //政法先锋id
        List<Map<String, Object>> bindContent = activityLawPioneerBiz.getBindContent(LiveCommonEnum.LAW_ID, page, pcount);
        for(Map<String, Object> map : bindContent){
            map.put("share_url", map.get("share_url")+"&original="+map.get("source_type"));
        }

        newMap.put("users" ,activityinfoList);
        newMap.put("share" ,shareMap);
        newMap.put("list" ,bindContent);
        response.setData(newMap);
        return response;
    }


    /**
     *  app - 活动--优惠券列表页 没过期的
     */
    @RequestMapping(value = "/appGetUserCouponAvailableList", method = RequestMethod.POST)
    AppObjectResponse appGetUserCouponAvailableList(@RequestParam(value = "uid") Integer uid,
                                                    @RequestParam(value = "page") Integer page ,
                                                    @RequestParam(value = "pcount") Integer pcount){
        AppObjectResponse response = new AppObjectResponse();
        List<Map<String, Object>> map = userActivityCouponBiz.appGetUserCouponAvailableLis(uid ,page ,pcount);
        response.setData(map);
        return response;
    }

    /**
     *  app - 活动--优惠券列表页  过期的
     */
    @RequestMapping(value = "/appGetUserCouponList", method = RequestMethod.POST)
    AppObjectResponse appGetUserCouponList(@RequestParam(value = "uid") Integer uid,
                                                    @RequestParam(value = "page") Integer page ,
                                                    @RequestParam(value = "pcount") Integer pcount){
        AppObjectResponse response = new AppObjectResponse();
        List<Map<String, Object>> map = userActivityCouponBiz.appGetUserCouponList(uid ,page ,pcount);
        response.setData(map);
        return response;
    }
}