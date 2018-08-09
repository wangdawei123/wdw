package com.kanfa.news.app.rest;

import com.kanfa.news.app.biz.app.BroadcastBiz;
import com.kanfa.news.app.feign.IAppContentServiceFeign;
import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wdw
 * APP  内容 模块
 */
@RestController
@RequestMapping("burst")
public class BurstRest extends BaseRest {
    @Autowired
    private BroadcastBiz broadcastBiz;

    /**
     * 爆料列表API
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/getBurstList", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String,Object>> getBurstList(@RequestParam(value = "page",required = false) Integer page,
                                                             @RequestParam(value = "pcount",required = false) Integer pcount) {
        try{
            return broadcastBiz.getBurstList(page,pcount);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 新增爆料接口
     * @param user_id
     * @param user_name
     * @param address
     * @param point
     * @param remark
     * @param content
     * @param images
     * @param videos
     * @return
     */
    @RequestMapping(value = "/addBurst", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String,Object>> addBurst(@RequestParam("user_id") Integer user_id,
                                                           @RequestParam("user_name") String user_name,
                                                           @RequestParam(value = "address",required = false) String address,
                                                           @RequestParam(value = "point",required = false) String point,
                                                           @RequestParam("remark") String remark,
                                                           @RequestParam(value = "content",required = false) String content,
                                                           @RequestParam(value = "images",required = false) String images,
                                                           @RequestParam(value = "videos",required = false) String videos) {
        try{
            return broadcastBiz.addBurst(user_id,user_name,address,point,remark,content,images,videos);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 爆料绑定内容列表
     * @param burst_id
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/getBindNews", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String,Object>> getBindNews(@RequestParam("burst_id") Integer burst_id,
                                                                 @RequestParam(value = "page",required = false) Integer page,
                                                                 @RequestParam(value = "pcount",required = false) Integer pcount) {
        try{
            return broadcastBiz.getBindNews(burst_id,page,pcount);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

}
