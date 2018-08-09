package com.kanfa.news.app.feign;

import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author wdw
 * APP  内容 模块
 *
 */
@FeignClient(name = "service-provider-news")
public interface IAppContentServiceFeign {
    /**
     * 文章详情页接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/index/getArticleDetail", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getArticleDetail(@RequestParam("cate") String cate,
                                                            @RequestParam("cid") String cid,
                                                            @RequestParam("uid") Integer uid);

    /**
     * 直播详情页接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/index/getBroadcastDetail", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getBroadcastDetail(@RequestParam("cate") Integer cate,
                                                              @RequestParam("cid") Integer cid);

    /**
     * 获取频道的内容列表
     * @param chanId
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/index/getContentList", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getContentList(@RequestParam("chanId") Integer chanId,
                                                          @RequestParam(value = "page", required = false) Integer page,
                                                          @RequestParam(value = "pcount", required = false) Integer pcount);

    /**
     * 图集详情页接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/index/getImageDetail", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getImageDetail(@RequestParam(value = "cate", required = false) Integer cate,
                                                          @RequestParam("cid") Integer cid);

    /**
     * 首页搜索列表
     * @param keyword
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/index/getSearchList", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getSearchList(@RequestParam("keyword") String keyword,
                                                         @RequestParam(value = "page", required = false) Integer page,
                                                         @RequestParam(value = "pcount", required = false) Integer pcount);

    /**
     * 专题详情页接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/index/getSpecialDetail", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getSpecialDetail(@RequestParam("cate") Integer cate,
                                                            @RequestParam(value = "cid", required = false) Integer cid);

    /**
     * 专题详情页接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/index/getVideoDetail", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getVideoDetail(@RequestParam(value = "cate", required = false) Integer cate,
                                                          @RequestParam("cid") Integer cid);

    /**
     * VR详情页接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/index/getVrDetail", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getVrDetail(@RequestParam("cate") Integer cate,
                                                       @RequestParam(value = "cid", required = false) Integer cid);

    /**
     * VR详情页接口
     * @param cate
     * @param cid
     * @return
     */
    @RequestMapping(value = "/index/getNewSpecial", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getNewSpecial(@RequestParam("cate") Integer cate,
                                                         @RequestParam("cid") Integer cid);


    /**
     * 单篇内容收藏状态
     * @param uid
     * @param cid
     * @return
     */
    @RequestMapping(value = "/index/getOneFavStatus", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getOneFavStatus(@RequestParam(value = "uid") Integer uid,
                                                           @RequestParam(value = "cid") Integer cid);


    /**
     * 爆料列表API
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/index/getBurstList", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getBurstList(@RequestParam(value = "page", required = false) Integer page,
                                                        @RequestParam(value = "pcount", required = false) Integer pcount);

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
    @RequestMapping(value = "/index/addBurst", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> addBurst(@RequestParam("user_id") Integer user_id,
                                                    @RequestParam("user_name") String user_name,
                                                    @RequestParam(value = "address", required = false) String address,
                                                    @RequestParam(value = "point", required = false) String point,
                                                    @RequestParam("remark") String remark,
                                                    @RequestParam(value = "content", required = false) String content,
                                                    @RequestParam(value = "images", required = false) String images,
                                                    @RequestParam(value = "videos", required = false) String videos);


    /**
     * 爆料绑定内容列表
     * @param burst_id
     * @param page
     * @param pcount
     * @return
     */
    @RequestMapping(value = "/index/getBindNews", method = RequestMethod.POST)
    ObjectRestResponse<Map<String,Object>> getBindNews(@RequestParam("burst_id") Integer burst_id,
                                                       @RequestParam(value = "page", required = false) Integer page,
                                                       @RequestParam(value = "pcount", required = false) Integer pcount);
}
