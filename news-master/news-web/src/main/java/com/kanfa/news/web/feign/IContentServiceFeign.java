package com.kanfa.news.web.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.entity.Content;
import com.kanfa.news.web.feign.callBack.ContentServiceFallBack;
import com.kanfa.news.web.vo.channel.ContentImageInfo;
import com.kanfa.news.web.vo.channel.ContentInfo;
import com.kanfa.news.web.vo.comment.CommentInfo;
import com.kanfa.news.web.vo.news.ContentDetailInfo;
import com.kanfa.news.web.vo.news.LoveInfo;
import com.kanfa.news.web.vo.news.ViewContentInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/3/5 17:28
 */
@FeignClient(name = "service-provider-news", fallback = ContentServiceFallBack.class)
public interface IContentServiceFeign {

    /**
     * 根据id查询内容
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/{id}",method = RequestMethod.GET)
    ObjectRestResponse<Content> get(@PathVariable("id") int id);

    /**
     * 根据id查询内容+内容详情表
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/content_Article/{id}",method = RequestMethod.GET)
    ContentInfo getContentArticle(@PathVariable("id") int id);

    @RequestMapping(value = "/content/hotgetlist",method = RequestMethod.GET)
    List<ContentInfo> hotgetlist(@RequestParam("contentType") int contentType);

    /**
     * 模糊搜索--下拉页面异步加载
     * @param searchKey
     * @return
     */
    @RequestMapping(value = "/content/searchLoad",method = RequestMethod.GET)
    List<ContentImageInfo> searchLoad(@RequestParam("searchKey") String searchKey,
                                @RequestParam(name = "offset") Integer offset,
                                @RequestParam(name = "limit") Integer limit);


    /**
     * 根据id查询内容+内容详情表
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/getContentImageById/{id}",method = RequestMethod.GET)
    List<ContentImageInfo> getContentImageById(@PathVariable("id") int id);

    /**
     * 異步請求內容查询PC端
     * @param
     * @return
     */
    @RequestMapping(value = "/content/getContentActive",method = RequestMethod.POST)
    List<ContentImageInfo> getContentActive(@RequestBody ContentImageInfo entity);

    /**
     * 分页查询PC端
     * @param
     * @return
     */
    @RequestMapping(value = "/content/pagePC",method = RequestMethod.POST)
    HashMap<String,Object> getContentPagePC(@RequestBody ContentImageInfo entity);

    /**
     * 查询内容详情
     * @param cid
     * @param cate
     * @param fontsize
     * @param ,  @RequestParam(name = "original",required = false) String original,  @RequestParam(name = "activity",required = false) String activity,
     * @param devid
     * @param uid
     * @return
     */
    @RequestMapping(value = "/content/getNewIndex",method = RequestMethod.GET)
    ContentDetailInfo getNewIndex(@RequestParam(name = "cid") Integer cid, @RequestParam(name = "cate") Integer cate, @RequestParam(name = "fontsize",required = false)  String fontsize,@RequestParam(name = "devid", required=false) String devid,@RequestParam(name = "uid", required=false) Integer uid);

    @RequestMapping(value = "/content/updateViews",method = RequestMethod.GET)
    Integer updateViews( @RequestParam(name = "id") Integer id);


    /**
     * 查询用户点赞
     * @param love
     * @return
     */
    @RequestMapping(value = "/love/getLoveList",method = RequestMethod.POST)
    List<LoveInfo> getLoveList(@RequestBody LoveInfo love);

    /**
     * 分享内容
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/getNewDetail",method = RequestMethod.GET)
    ObjectRestResponse<ContentDetailInfo> getNewDetail(@RequestParam(name = "id") Integer id);

    /**
     * 更新浏览量
     * @param viewContentInfo
     */
    @RequestMapping(value = "/content/updateContentView",method = RequestMethod.PUT)
    ObjectRestResponse updateContentView(@RequestBody ViewContentInfo viewContentInfo);

    /**
     * 获取微信签名
     * @param
     * @return
     */
    @RequestMapping(value = "/content/getWeiXinShareMap",method = RequestMethod.GET)
    Map<String, Object> getSignPackage(@RequestParam(value = "url") String url);

    /**
     * 视频详情页h5分享页接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/getVideoDetail",method = RequestMethod.POST)
    ObjectRestResponse getVideoDetail(@RequestParam("id")Integer id);

    /**
     * 视频专辑分享页接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/getVideoAlbum",method = RequestMethod.POST)
    ObjectRestResponse getVideoAlbum(@RequestParam("id")Integer id);



    @RequestMapping(value = "/content/updateVideoViews",method = RequestMethod.GET)
    ObjectRestResponse updateVideoViews(@RequestParam("id") Integer id,
                                        @RequestParam("Integer fromApp") Integer fromApp);

    /**
     * 查询评论
     * @param commentInfo
     * @return
     */
    @RequestMapping(value = "/comment/getCommentList",method = RequestMethod.POST)
    ObjectRestResponse getCommentList(@RequestBody CommentInfo commentInfo);

}
