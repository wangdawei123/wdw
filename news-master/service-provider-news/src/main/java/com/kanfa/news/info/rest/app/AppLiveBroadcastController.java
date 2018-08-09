package com.kanfa.news.info.rest.app;

import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ContentBiz;
import com.kanfa.news.info.biz.app.AppLiveBroadcastBiz;
import com.kanfa.news.info.entity.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

/**
 *  APP直播 Live broadcast 模块
 *  Created by wdw on 2018-3-29.
 */
@RestController
@RequestMapping("aliyunbroadcast")
public class AppLiveBroadcastController extends BaseController<ContentBiz, Content> {
    @Autowired
    private AppLiveBroadcastBiz appLiveBroadcastBiz;

    /**
     * 直播认证手机号验证码请求
     * @param code
     * @param authcode
     * @param sessionid
     * @param phone
     * @return
     */
    @RequestMapping(value = "/checkSMSCode",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> checkSMSCode(@RequestParam(name = "code") String code,
                                                               @RequestParam(name = "authcode") String authcode,
                                                               @RequestParam(name = "sessionid") String sessionid,
                                                               @RequestParam(name = "phone") Integer phone){
        try{
            return appLiveBroadcastBiz.checkSMSCode(code,authcode,sessionid,phone);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }
    /**
     * APP 直播获取地址接口
     * @param
     * @return
     */
    @RequestMapping(value = "/getBroadcastAddress",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getBroadcastAddress(){
        try{
            return appLiveBroadcastBiz.getBroadcastAddress(request);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     *  APP 创建直播(阿里云)接口
     * @param title
     * @param address
     * @param phone
     * @param sessionid
     * @param uid
     * @return
     */
    @RequestMapping(value = "/getBroadcastInfo",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getBroadcastInfo(@RequestParam(name = "title") String title,
                                                                   @RequestParam(name = "address") String address,
                                                                   @RequestParam(name = "phone") String phone,
                                                                   @RequestParam(name = "sessionid") String sessionid,
                                                                   @RequestParam(name = "uid") Integer uid){
        try{
            return appLiveBroadcastBiz.getBroadcastInfo(title,address,phone,sessionid,uid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * APP 直播在线人数接口(阿里云)接口
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getOnlineUserNum",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getOnlineUserNum(@RequestParam(name = "cid") Integer cid){
        try{
            return appLiveBroadcastBiz.getOnlineUserNum(cid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * APP 新版律师来了接口(直播表)接口
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/getBroadcastListNew",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getBroadcastListNew(@RequestParam(value = "page" ,required = false,defaultValue = "1") Integer page,@RequestParam(name = "pcount",required = false,defaultValue = "10") Integer pcount){
        try{
            return appLiveBroadcastBiz.getBroadcastListNew(page,pcount);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }


    public static void main (String[] args){
        ArrayList<Map> list=new ArrayList();
        String dd=new String("23");
        String ee=new String("23");
        System.out.println(dd.equals(ee));
        System.out.println(dd==ee);
        long a= 123;
        long b=123;
        System.out.println(a & b);
        double d = 123.4586;
        long l = Double.doubleToLongBits(d);
        System.out.println(l);

    }
}
