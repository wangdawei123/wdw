package com.kanfa.news.app.feign;

import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author wdw
 * APP  频道 模块
 *
 */
@FeignClient(name = "service-provider-news")
public interface IAliyunbroadcastServiceFeign {
    /**
     * 获取频道列表
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/aliyunbroadcast/getBroadcastListNew", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getBroadcastListNew(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "pcount", required = false) Integer pcount);

    /**
     * 直播在线人数接口(阿里云)
     * @param cid
     * @return
     */
    @RequestMapping(value = "/aliyunbroadcast/getOnlineUserNum", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getOnlineUserNum(@RequestParam("cid") String cid);

    /**
     * 创建直播(阿里云)
     * @param title
     * @param address
     * @param phone
     * @param sessionid
     * @param uid
     * @return
     */
    @RequestMapping(value = "/aliyunbroadcast/getBroadcastInfo", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getBroadcastInfo(@RequestParam(name = "title") String title,
                                                            @RequestParam(name = "address") String address,
                                                            @RequestParam(name = "phone") String phone,
                                                            @RequestParam(name = "sessionid") String sessionid,
                                                            @RequestParam(name = "uid") Integer uid);

    /**
     * 直播获取地址
     * @return
     */
    @RequestMapping(value = "/aliyunbroadcast/getBroadcastAddress", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getBroadcastAddress();

    /**
     * 直播认证手机号验证码请求
     * @param code
     * @param authcode
     * @param sessionid
     * @param phone
     * @return
     */
    @RequestMapping(value = "/aliyunbroadcast/checkSMSCode", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> checkSMSCode(@RequestParam(name = "code") String code,
                                                        @RequestParam(name = "authcode") String authcode,
                                                        @RequestParam(name = "sessionid") String sessionid,
                                                        @RequestParam(name = "phone") Integer phone);


}
