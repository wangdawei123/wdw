package com.kanfa.news.app.rest;

import com.kanfa.news.app.biz.app.AppLiveBroadcastBiz;
import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wdw
 */
@RestController
@RequestMapping("aliyunbroadcast")
public class AliyunbroadcastRest extends BaseRest {
    @Autowired
    private AppLiveBroadcastBiz appLiveBroadcastBiz;

    /**
     * 新版律师来了接口(直播表)
     *
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/getBroadcastListNew", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> getBroadcastListNew(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                                       @RequestParam(value = "pcount", required = false, defaultValue = "10") Integer pcount) {
        try {
            return appLiveBroadcastBiz.getBroadcastListNew(page, pcount);
        } catch (Exception e) {
            ObjectRestResponse<Map<String, Object>> result = new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 直播在线人数接口(阿里云)
     *
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getOnlineUserNum", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> getOnlineUserNum(@RequestParam("cid") Integer cid) {
        try {
            return appLiveBroadcastBiz.getOnlineUserNum(cid);
        } catch (Exception e) {
            ObjectRestResponse<Map<String, Object>> result = new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 创建直播(阿里云)
     *
     * @param title
     * @param address
     * @param phone
     * @param sessionid
     * @param uid
     * @return
     */
    @RequestMapping(value = "/getBroadcastInfo", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> getBroadcastInfo(@RequestParam(name = "title") String title,
                                                                    @RequestParam(name = "address") String address,
                                                                    @RequestParam(name = "phone") String phone,
                                                                    @RequestParam(name = "sessionid") String sessionid,
                                                                    @RequestParam(name = "uid") Integer uid) {
        try {
            return appLiveBroadcastBiz.getBroadcastInfo(title, address, phone, sessionid, uid);
        } catch (Exception e) {
            ObjectRestResponse<Map<String, Object>> result = new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 直播获取地址
     *
     * @return
     */
    @RequestMapping(value = "/getBroadcastAddress", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> getBroadcastAddress() {
        try {
            return appLiveBroadcastBiz.getBroadcastAddress(request);
        } catch (Exception e) {
            ObjectRestResponse<Map<String, Object>> result = new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 直播认证手机号验证码请求
     *
     * @param code
     * @param authcode
     * @param sessionid
     * @param phone
     * @return
     */
    @RequestMapping(value = "/checkSMSCode", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> checkSMSCode(@RequestParam(name = "code") String code,
                                                                @RequestParam(name = "authcode") String authcode,
                                                                @RequestParam(name = "sessionid") String sessionid,
                                                                @RequestParam(name = "phone") Integer phone) {
        try {
            return appLiveBroadcastBiz.checkSMSCode(code, authcode, sessionid, phone);
        } catch (Exception e) {
            ObjectRestResponse<Map<String, Object>> result = new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }


}
