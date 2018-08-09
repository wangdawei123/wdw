package com.kanfa.news.app.rest;

import com.kanfa.news.app.biz.ActivityLawPioneerBiz;
import com.kanfa.news.app.entity.ActivityLawPioneer;
import com.kanfa.news.app.feign.IActivityServiceFeign;
import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ffy
 */
@RestController
@RequestMapping("/activity")
public class ActivityRest extends BaseRest {
    @Autowired
    private IActivityServiceFeign activityServiceFeign;

    @Autowired
    private ActivityLawPioneerBiz activityLawPioneerBiz;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * app -青春微普法活动--首页接口  ---- 暂停，后延
     */
    @RequestMapping(value = "/getLawList", method = RequestMethod.POST)
    public AppObjectResponse getLawList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "pcount", defaultValue = "10") Integer pcount) {
        AppObjectResponse response = new AppObjectResponse();
        Map<String, Object> newMap = new HashMap<>(5);

        //机构或个人列表
        List<Map<String, Object>> activityinfoList = new ArrayList<>();
        List<ActivityLawPioneer> list = activityLawPioneerBiz.getLawList();
        for (ActivityLawPioneer lawPioneer : list) {
            Map<String, Object> map = new HashMap<>(5);
            map.put("iconid", lawPioneer.getId());
            map.put("iconname", lawPioneer.getTitle());
            map.put("icon", lawPioneer.getIcon());
            activityinfoList.add(map);
        }

        Map<String, Object> shareMap = new HashMap<>(5);
        shareMap.put("title", LiveCommonEnum.ACTIVITY_LAW_TITLE);
        shareMap.put("desc", LiveCommonEnum.ACTIVITY_LAW_DESC);
        shareMap.put("icon", LiveCommonEnum.ACTIVITY_LAW_ICON);
        shareMap.put("url", LiveCommonEnum.ACTIVITY_LAW_URL);

        //政法先锋id
        List<Map<String, Object>> bindContent = activityLawPioneerBiz.getBindContent(LiveCommonEnum.LAW_ID, page, pcount);
        for (Map<String, Object> map : bindContent) {
            map.put("share_url", map.get("share_url") + "&original=" + map.get("source_type"));
        }

        newMap.put("users", activityinfoList);
        newMap.put("share", shareMap);
        newMap.put("list", bindContent);
        response.setData(newMap);
        return response;
    }

    /**
     * app - 抽奖消息列表接口
     */
    @RequestMapping(value = "/getMessage", method = RequestMethod.POST)
    public AppObjectResponse getMessage(@RequestParam(value = "sessionid") String sessionid) {
        Object o = this.redisTemplate.opsForValue().get(LiveCommonEnum.APP_USER_SESSIONID + sessionid);
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        Map<String, Object> map = new HashMap<>(6);
        if (o == null) {
            //用户未登陆
            map.put("list", "");
            response.setData(map);
            response.setStatus(AppCommonMessageEnum.OK.key());
            response.setMessage(AppCommonMessageEnum.OK.value());
            return response;
        }

        Integer uid = (Integer) o;
        response = activityServiceFeign.getMessage(uid);
        return response;
    }

    /**
     * app -- 活动--优惠券列表页 没过期的  没法判断使用没使用
     */
    @RequestMapping(value = "/coupon/list", method = RequestMethod.POST)
    public AppObjectResponse couponList(@RequestParam(value = "sessionid") String sessionid,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "pcount", defaultValue = "8") Integer pcount) {
        Object o = this.redisTemplate.opsForValue().get(LiveCommonEnum.APP_USER_SESSIONID + sessionid);
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        if (o == null) {
            //用户未登陆
            response.setStatus(AppCommonMessageEnum.USER_NOT_LOGIN.key());
            response.setMessage(AppCommonMessageEnum.USER_NOT_LOGIN.value());
            return response;
        }
        Integer uid = (Integer) o;
        response = activityServiceFeign.appGetUserCouponAvailableList(uid, page, pcount);
        return response;
    }

    /**
     * 活动--优惠券列表页 过期的
     */
    @RequestMapping(value = "/coupon/past", method = RequestMethod.POST)
    public AppObjectResponse couponPast(@RequestParam(value = "sessionid") String sessionid,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "pcount", defaultValue = "8") Integer pcount) {
        Object o = this.redisTemplate.opsForValue().get(LiveCommonEnum.APP_USER_SESSIONID + sessionid);
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        if (o == null) {
            //用户未登陆
            response.setStatus(AppCommonMessageEnum.USER_NOT_LOGIN.key());
            response.setMessage(AppCommonMessageEnum.USER_NOT_LOGIN.value());
            return response;
        }
        Integer uid = (Integer) o;
        response = activityServiceFeign.appGetUserCouponList(uid, page, pcount);
        return response;
    }


}
