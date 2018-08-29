package com.kanfa.news.app.rest;

import com.kanfa.news.app.biz.ContentBiz;
import com.kanfa.news.app.biz.app.BroadcastBiz;
import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author wdw
 * APP  内容 模块
 */
@RestController
@RequestMapping("index")
public class AppContentRest extends BaseRest {
    @Autowired
    private ContentBiz contentBiz;

    @Autowired
    private BroadcastBiz broadcastBiz;

    /**
     * 文章详情页接口
     *
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getArticleDetail", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> getArticleDetail(@RequestParam(value = "cate", required = false, defaultValue = " ") String cate,
                                                                    @RequestParam("cid") String cid,
                                                                    @RequestParam(value = "uid",required = false) Integer uid) {


        try {
            return contentBiz.getArticleDetail(cate, cid, uid);

        } catch (Exception e) {
            ObjectRestResponse<Map<String, Object>> result = new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 直播详情页接口
     *
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getBroadcastDetail", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> getBroadcastDetail(@RequestParam(value = "cate",required = false, defaultValue = " ") Integer cate,
                                                                      @RequestParam("cid") Integer cid,
                                                                      @RequestParam(value = "uid",required = false) Integer uid) {
        try {
            return broadcastBiz.getBroadcastDetail(cate, cid,uid);
        } catch (Exception e) {
            ObjectRestResponse<Map<String, Object>> result = new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 获取频道的内容列表
     *
     * @param chanId
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/getContentList", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> getContentList(@RequestParam("chanId") Integer chanId,
                                                                  @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                                  @RequestParam(value = "pcount", required = false, defaultValue = "10") Integer pcount) {
        try {
            return broadcastBiz.getContentList(chanId, page, pcount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            ObjectRestResponse<Map<String, Object>> result = new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 首页添加推荐 改版的新接口   （添加推荐栏目）
     * new获取频道的内容列表
     * @param chanId      频道ID
     * @param signtime    请求时间戳
     * @param device_imei imei
     * @param channel     渠道
     * @param refresh     是否刷新 1刷新，默认0
     * @param order       推荐点击编辑推荐，次数设置为new，默认为空
     * @param maxid       上一次最大ID
     * @param page        页码
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/getChannelContent", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> getChannelContent(@RequestParam("chanId") Integer chanId,
                                                                     @RequestParam(value = "signtime",required = false) Long signtime,
                                                                     @RequestParam(value = "device_imei",required = false) String device_imei,
                                                                     @RequestParam(value = "channel",required = false) String channel,
                                                                     @RequestParam(value = "refresh",required = false) Integer refresh,
                                                                     @RequestParam(value = "order",required = false) String order,
                                                                     @RequestParam(value = "maxid",required = false) String maxid,
                                                                     @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                                     @RequestParam(value = "pcount", required = false, defaultValue = "10") Integer pcount) {
        try {
            return broadcastBiz.getNewContentList(chanId,signtime,device_imei,channel,refresh,order,maxid, page, pcount);
        } catch (Exception e) {
            e.printStackTrace();
            ObjectRestResponse<Map<String, Object>> result = new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 图集详情页接口
     *
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getImageDetail", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> getImageDetail(@RequestParam(value = "cate", required = false, defaultValue = " ") Integer cate,
                                                                  @RequestParam("cid") Integer cid,
                                                                  @RequestParam(value = "uid",required = false) Integer uid) {
        try{
            return broadcastBiz.getImageDetail(cate,cid,uid);
        }catch (Exception e){
            e.printStackTrace();
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 首页搜索列表--旧版，停用
     *
     * @param keyword
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/getSearchListOld", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> getSearchList(@RequestParam("keyword") String keyword,
                                                                 @RequestParam(value = "page", required = false) Integer page,
                                                                 @RequestParam(value = "pcount", required = false) Integer pcount) {
        try{
            return broadcastBiz.getSearchList(keyword,page,pcount);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            System.identityHashCode("dd");
            return result;
        }
    }

    /**
     * 专题详情页接口
     *
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getSpecialDetail", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> getSpecialDetail(@RequestParam(value = "cate", required = false, defaultValue = " ") Integer cate,
                                                                    @RequestParam(value = "cid", required = false) Integer cid,
                                                                    @RequestParam(value = "uid",required = false) Integer uid) {
        try{
            return broadcastBiz.getSpecialDetail(cate,cid,uid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 视频详情页接口
     *
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getVideoDetail", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> getVideoDetail(@RequestParam(value = "cate", required = false, defaultValue = " ") Integer cate,
                                                                  @RequestParam("cid") Integer cid,
                                                                  @RequestParam(value = "uid",required = false) Integer uid) {
        try{
            return broadcastBiz.getVideoDetail(cate,cid,uid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * VR详情页接口
     *
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getVrDetail", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> getVrDetail(@RequestParam(value = "cate", required = false, defaultValue = " ") Integer cate,
                                                               @RequestParam("cid") Integer cid,
                                                               @RequestParam(value = "uid",required = false) Integer uid) {
        try{
            return broadcastBiz.getVrDetail(cate,cid,uid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * 新版视频接口，数组第三个为滚动，如果有广告，4和9为广告
     * @param devID         devID号
     * @param uid           用户id
     * @param signtime      时间码
     * @param videoType   	99999为推荐，其它为视频分类
     * @param deviceImei  	imei码
     * @param channel      	渠道号
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/getVideoList", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> getVideoList(@RequestParam("devID") String devID,
                                                                @RequestParam(value = "chanId",required = false,defaultValue = "59") Integer chanId,
                                                                @RequestParam(value = "uid",required = false) Integer uid,
                                                                @RequestParam(value = "signtime",required = false) Long signtime,
                                                                @RequestParam(value = "video_type",required = false,defaultValue = "99999") Integer videoType,
                                                                @RequestParam(value = "device_imei") String deviceImei,
                                                                @RequestParam(value = "channel") String channel,
                                                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                                @RequestParam(value = "pcount", required = false, defaultValue = "10") Integer pcount) {
        try{
            return broadcastBiz.getVideoList(devID,chanId,uid,signtime,videoType,deviceImei,channel,page,pcount);
        }catch (Exception e){
            e.printStackTrace();
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }


}
