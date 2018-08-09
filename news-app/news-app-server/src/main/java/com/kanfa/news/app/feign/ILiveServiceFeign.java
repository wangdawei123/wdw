package com.kanfa.news.app.feign;

import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author Jezy
 */
@FeignClient(name = "service-provider-news")
public interface ILiveServiceFeign {
    @RequestMapping(value = "/login/updatePlayViews", method = RequestMethod.POST)
    ObjectRestResponse updatePlayViews(@RequestParam("id") Integer id,
                                       @RequestParam("fromApp") Integer fromApp);

    @RequestMapping(value = "/live/getToken", method = RequestMethod.POST)
    @ResponseBody
    ObjectRestResponse<String> getToken(@RequestParam(value = "sessionid") Integer sessionId,
                                        @RequestParam(value = "chatroomID") String chatroomId);

    /**
     * APP内容-获取直播及庭审首页
     * @param liveTypId
     * @return AppObjectResponse
     */
    @RequestMapping(value = "/live/getLiveAll",method = RequestMethod.POST)
    AppObjectResponse<Object> getLiveAll(@RequestParam(value = "liveTypId") Integer liveTypId,
                                         @RequestParam("uid") String uid);


    /**
     * 直播庭审详情页接口
     * @param liveId
     * @param liveTypeId
     * @return
     */
    @RequestMapping(value = "/live/getLiveDetail", method = RequestMethod.POST)
    AppObjectResponse<Map<String, Object>> getLiveDetail(@RequestParam("liveId") Integer liveId,
                                                         @RequestParam("liveTypeId") Integer liveTypeId);
    /**
     * APP内容-获取预告
     */
    @RequestMapping(value = "/live/getPriviewList",method = RequestMethod.POST)
    AppObjectResponse<Object> getPriviewList(@RequestParam(value = "liveTypId") Integer liveTypId,
                                             @RequestParam(value = "page") Integer page);

    /**
     * APP内容-直播列表及筛选
     */
    @RequestMapping(value = "/live/getOnlineFilter" ,method = RequestMethod.POST)
    AppObjectResponse<Object> getOnlineFilter(@RequestParam(value = "liveTypId") Integer liveTypId,
                                              @RequestParam(value = "page") Integer page,
                                              @RequestParam(value = "pcount") Integer pcount,
                                              @RequestParam(value = "case_type", required = false) Integer case_type,
                                              @RequestParam(value = "court", required = false) Integer court,
                                              @RequestParam(value = "court_level", required = false) Integer court_level,
                                              @RequestParam(value = "province_id", required = false) Integer province_id,
                                              @RequestParam(value = "stat", required = false) Integer stat,
                                              @RequestParam(value = "signtime", required = false) Integer signtime,
                                              @RequestParam(value = "type", required = false) Integer type);

}
