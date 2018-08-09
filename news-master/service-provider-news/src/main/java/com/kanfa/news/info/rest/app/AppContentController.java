package com.kanfa.news.info.rest.app;

import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ContentBiz;
import com.kanfa.news.info.biz.app.BroadcastBiz;
import com.kanfa.news.info.entity.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *  APP内容 模块
 *  Created by wdw on 2018/3/16.
 */
@RestController
@RequestMapping("index")
public class AppContentController  extends BaseController<ContentBiz, Content> {
    @Autowired
    private ContentBiz contentBiz;
    @Autowired
    private BroadcastBiz broadcastBiz;

    /**
     * APP内容-文章详情页接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getArticleDetail",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getArticleDetail(@RequestParam(value = "cate",required = false) String cate,
                                                                   @RequestParam("cid") String cid,
                                                                   @RequestParam("uid") Integer uid){
        try{
            return contentBiz.getArticleDetail(cate,cid,uid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * APP内容-直播详情页接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getBroadcastDetail",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getBroadcastDetail(@RequestParam(name = "cate",required = false) Integer cate,
                                                                     @RequestParam("cid") Integer cid){
        try{
            return broadcastBiz.getBroadcastDetail(cate,cid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * APP内容-获取频道的内容列表
     * @param chanId
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/getContentList",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getContentList(@RequestParam("chanId") Integer chanId,
                                                                 @RequestParam(value = "page",required = false ,defaultValue = "1") Integer page,
                                                                 @RequestParam(value = "pcount",required = false,defaultValue = "10") Integer pcount){
        try{
            return broadcastBiz.getContentList(chanId,page,pcount);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * APP内容-图集详情页接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getImageDetail",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getImageDetail(@RequestParam(value = "cate",required = false) Integer cate,
                                                                 @RequestParam("cid") Integer cid){
        try{
            return broadcastBiz.getImageDetail(cate,cid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }


    /**
     * APP内容-首页搜索列表接口
     * @param keyword
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/getSearchList",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getSearchList(@RequestParam("keyword") String keyword,
                                                                @RequestParam(value = "page",required = false) Integer page,
                                                                @RequestParam(value = "pcount",required = false) Integer pcount){
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
     * APP内容-专题详情页接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getSpecialDetail",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getSpecialDetail(@RequestParam("cate") Integer cate,
                                                                   @RequestParam(value = "cid",required = false) Integer cid){
        try{
            return broadcastBiz.getSpecialDetail(cate,cid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * APP内容-视频详情页接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getVideoDetail",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getVideoDetail(@RequestParam(value = "cate",required = false) Integer cate,
                                                                 @RequestParam("cid") Integer cid){
        try{
            return broadcastBiz.getVideoDetail(cate,cid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }


    /**
     * APP内容-VR详情页接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getVrDetail",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getVrDetail(@RequestParam("cate") Integer cate,
                                                              @RequestParam(value = "cid",required = false) Integer cid){
        try{
            return broadcastBiz.getVrDetail(cate,cid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * APP内容-新版专题接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getNewSpecial",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getNewSpecial(@RequestParam("cate") Integer cate,
                                                                @RequestParam("cid") Integer cid){
        try{
            return broadcastBiz.getNewSpecial(cate,cid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * APP内容-单篇内容收藏状态 接口
     * @param uid
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getOneFavStatus",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getOneFavStatus(@RequestParam("uid") Integer uid,
                                                                  @RequestParam("cid") Integer cid){
        try{
            return broadcastBiz.getOneFavStatus(uid,cid);
        }catch (Exception e){
            ObjectRestResponse<Map<String,Object>> result=new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }

    /**
     * APP内容-爆料列表API  接口
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/getBurstList",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getBurstList(@RequestParam(value = "page",required = false) Integer page,
                                                               @RequestParam(value = "pcount",required = false) Integer pcount){
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
     * APP内容-新增爆料接口 接口
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
    @RequestMapping(value = "/addBurst",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> addBurst(@RequestParam("user_id") Integer user_id,
                                                           @RequestParam("user_name") String user_name,
                                                           @RequestParam(value = "address",required = false) String address,
                                                           @RequestParam(value = "point",required = false) String point,
                                                           @RequestParam("remark") String remark,
                                                           @RequestParam(value = "content",required = false) String content,
                                                           @RequestParam(value = "images",required = false) String images,
                                                           @RequestParam(value = "videos",required = false) String videos){
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
     * APP内容-爆料绑定内容列表 接口
     * @param burst_id
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/getBindNews",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getBindNews(@RequestParam("burst_id") Integer burst_id,
                                                              @RequestParam("page") Integer page,
                                                              @RequestParam("pcount") Integer pcount){
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
