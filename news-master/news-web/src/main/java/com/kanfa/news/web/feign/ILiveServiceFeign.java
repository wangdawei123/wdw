package com.kanfa.news.web.feign;

import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Jezy
 */
@FeignClient(name = "service-provider-news")
public interface ILiveServiceFeign {
    /**
     * A分享页
     */
    @RequestMapping(value = "/live/indexPage",method = RequestMethod.POST)
    ObjectRestResponse<Object> indexPage(@RequestParam(value = "id") Integer id);

    /**
     * 专题h5页面接口，app内外使用
     */
    @RequestMapping(value = "/special/getNewSpecial",method = RequestMethod.POST)
    ObjectRestResponse<Object> getNewSpecial(@RequestParam(value = "cid") Integer cid,@RequestParam(value = "cate") Integer cate);


    /**
     * 庭审直播详情页
     */
    @RequestMapping(value = "/live/getDetailData",method = RequestMethod.POST)
    Map<String, Object> getDetailData(@RequestParam(value = "liveId") Integer liveId);


    /**
     * 庭审直播详情页
     */
    @RequestMapping(value = "/live/getWebLiveDetail",method = RequestMethod.POST)
    AppObjectResponse<Object> getWebLiveDetail(@RequestParam(value = "liveId") Integer liveId,
                                               @RequestParam(value = "sessionid",required = false) String sessionid);

    /**
     * 增加视频播放量
     */
    @RequestMapping(value = "/live/updatePlayViews",method = RequestMethod.GET)
    Integer updatePlayViews(@RequestParam("id") Integer id,
                             @RequestParam("Integer fromApp") Integer fromApp);

    @RequestMapping(value = "/live/updateViews",method = RequestMethod.GET)
    Integer updateViews(@RequestParam("id") Integer id);

}
