package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.*;
import com.kanfa.news.admin.vo.channel.ChannelContentCardInfo;
import com.kanfa.news.admin.vo.channel.ChannelContentInfo;
import com.kanfa.news.admin.vo.channel.ContentInfo;
import com.kanfa.news.admin.vo.comment.CommentInfo;
import com.kanfa.news.admin.vo.content.*;
import com.kanfa.news.admin.vo.my.MyContentInfo;
import com.kanfa.news.admin.vo.video.*;
import com.kanfa.news.admin.vo.vr.VRContentAddInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/3/5 17:28
 */
@FeignClient(name = "service-provider-news")
public interface IContentServiceFeign {

    /**
     * 添加内容
     * @param entity
     * @return
     */
    @RequestMapping(value = "/content/addContent",method = RequestMethod.POST)
    ObjectRestResponse<ContentInfo> addContent(@RequestBody ContentInfo entity);
    /**
     * 根据id查询内容
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/{id}",method = RequestMethod.GET)
    ObjectRestResponse<Content> get(@PathVariable("id") int id);

    /**
     * 分页查询
     * @param entity
     * @return
     */
    @RequestMapping(value = "/content/page",method = RequestMethod.POST)
    @ResponseBody
    TableResultResponse<ContentInfo> getContentPage(@RequestBody ContentInfo entity);

    /**
     * 查询所有标签
     * @return
     */
    @RequestMapping(value = "/videoTag/all",method = RequestMethod.GET)
    @ResponseBody
    List<VideoTag> allTag();

    /**
     * 根据对应的内容Id查询标签
     * @param videoId
     * @return
     */
    @RequestMapping(value = "/videoTag/getTagListByConentId",method = RequestMethod.GET)
    ObjectRestResponse<List<VideoTag>> getTagListByConentId(@RequestParam("videoId") Integer videoId);

    @RequestMapping(value = "/content/update",method = RequestMethod.POST)
    ObjectRestResponse<ContentInfo> update(@RequestBody ContentInfo contentInfo);

//    @RequestMapping(value = "/baseUser/getUserByCid",method = RequestMethod.GET)
//    ObjectRestResponse<List<>> getUserByCid(@RequestParam("id") Integer id);


    /**
     * 新增视频
     * @param entity
     * @return
     */
    @RequestMapping(value = "/content/addVideoContent",method = RequestMethod.POST)
    ObjectRestResponse<VideoContentInfo> addVideoContent(@RequestBody VideoContentInfo entity);

    /**
     * 已回收列表
     * @param page,limit
     * @return
     */

    @RequestMapping(value = "/content/selectDeletes")
    TableResultResponse<RecycleBinInfo> selectDeletes(@RequestParam(name = "page") Integer page,
                                                      @RequestParam(name = "limit") Integer limit);

    /**
     * 已回收列表
     * @param page,limit,title
     * @return
     */
    @RequestMapping(value = "/content/selectSearchDeletes",method = RequestMethod.GET)
    TableResultResponse<RecycleBinInfo> selectSearchDeletes(@RequestParam(name = "page") Integer page,
                                                       @RequestParam(name = "limit") Integer limit,
                                                       @RequestParam(name = "title") String title);


    @RequestMapping(value = "/content/getContentPageForVideo",method = RequestMethod.POST)
    TableResultResponse<ContentInfo> getContentPageForVideo(@RequestBody ContentInfo contentInfo);


    /**
     * 回收站 已删除列表 的 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    ObjectRestResponse<Content> remove(@PathVariable("id") int id);


    /**
     * 回收站 已删除列表 的 编辑
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<Content> update(@PathVariable("id") Integer id, @RequestBody Content entity);

    @RequestMapping(value = "/content/addContentSubject",method = RequestMethod.POST)
    ObjectRestResponse<ContentInfo> addContentSubject(@RequestBody ContentInfo entity);

    @RequestMapping(value = "/content/getContent",method = RequestMethod.GET)
    ObjectRestResponse<ContentInfo> getContent(@RequestParam(name = "id") Integer id);

    @RequestMapping(value = "/content/batchDelete",method = RequestMethod.POST)
    ObjectRestResponse<Integer> batchDelete(@RequestBody List<Integer> ids,@RequestParam("ip") String ip,@RequestParam("uid") Integer uid);

    @RequestMapping(value = "/channelContent/updateChannelConent",method = RequestMethod.POST)
    ObjectRestResponse<Integer> updateChannelConent(@RequestBody ChannelContent channelContent);

    @RequestMapping(value = "/content/getContentSubject",method = RequestMethod.GET)
    ObjectRestResponse<ContentInfo> getContentSubject(@RequestParam("id") Integer id);

    @RequestMapping(value = "/contentArticle/getContentArticle",method = RequestMethod.POST)
    ObjectRestResponse<ContentArticleInfo> getContentArticle(@RequestBody ContentArticleInfo contentArticleInfo);

    @RequestMapping(value = "/comment/getPage")
    TableResultResponse<CommentInfo> getCommentPage(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/contentArticle/{id}",method = RequestMethod.GET)
    ObjectRestResponse<ContentArticle> getArticleImg(@PathVariable("id") Integer contentId);

    @RequestMapping(value = "/channelContentCard/getCardType",method = RequestMethod.POST)
    ObjectRestResponse<ChannelContentCardInfo> getCardType(@RequestBody ChannelContentCardInfo channelContentCardInfo);

    @RequestMapping(value = "/channelContentCard/updateCardType",method = RequestMethod.POST)
    ObjectRestResponse<Integer> updateCardType(@RequestBody ChannelContentCard channelContentCard);

    @RequestMapping(value = "/contentBroadcastBind",method = RequestMethod.POST)
    ObjectRestResponse<ContentBroadcastBind> bindContent(@RequestBody ContentBroadcastBind entity);

    @RequestMapping(value = "/content/getContentForBind",method = RequestMethod.GET)
    ObjectRestResponse<ContentInfo> getContentForBind(@RequestParam("id") Integer id);

    @RequestMapping(value = "/contentBroadcastBind/all",method = RequestMethod.GET)
    List<ContentBroadcastBind> getAllBind();

    @RequestMapping(value = "/contentBroadcastBind/unBindContent",method = RequestMethod.POST)
    ObjectRestResponse<ContentBroadcastBind> unBindContent(@RequestBody ContentBroadcastBind entity);

    @RequestMapping(value = "/contentBroadcastBind/getContentBind",method = RequestMethod.POST)
    List<ContentBroadcastBindInfo> getContentBind(@RequestBody ContentBroadcastBind contentBroadcastBind);

    /**
     * 绑定分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/contentBroadcastBind/getContentBindPage",method = RequestMethod.POST)
    TableResultResponse<ContentBroadcastBindInfo> getContentBindPage(@RequestParam Map<String, Object> params);

    /**
     * 视频列表分页查询
     * @param entity
     * @return
     */
    @RequestMapping(value = "/content/getVideoPage",method = RequestMethod.POST)
    TableResultResponse<VideoContentSelectInfo> getVideoPage(@RequestBody VideoSelectionInfo entity);



    /**
     * 得到一个视频的详尽信息
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/content/getVideoContent",method = RequestMethod.GET)
    ObjectRestResponse<VideoContentInfo> getVideoContent(@RequestParam("contentId") Integer contentId);

    /**
     * 修改视频的详尽信息
     * @param
     * @return
     */
    @RequestMapping(value = "/content/updateVideoContent",method = RequestMethod.POST)
    ObjectRestResponse<VideoContentInfo> updateVideoContent(@RequestBody VideoContentInfo entity);

    /**
     * 查询未审核的内容
     * @param contentInfo
     * @return
     */
    @RequestMapping(value = "/content/getContentNotCheckPage",method = RequestMethod.POST)
    TableResultResponse<ContentInfo> getContentNotCheckPage(@RequestBody ContentInfo contentInfo);

    /**
     * 新增图集
     * @param entity
     * @return
     */
    @RequestMapping(value = "/content/addContentAtlas",method = RequestMethod.POST)
    ObjectRestResponse<ContentInfo> addContentAtlas(@RequestBody ContentInfo entity);

    /**
     * 新增活动
     * @param entity
     * @return
     */
    @RequestMapping(value = "/content/addContentActivity",method = RequestMethod.POST)
    ObjectRestResponse<ActivityInfo> addContentActivity(@RequestBody ActivityInfo entity);

    /**
     * 更新活动
     * @param entity
     * @return
     */
    @RequestMapping(value = "/content/updateContentActivity",method = RequestMethod.POST)
    ObjectRestResponse<ActivityInfo> updateContentActivity(@RequestBody ActivityInfo entity);

    @RequestMapping(value = "/content/saveMogoLocation",method = RequestMethod.POST)
    ObjectRestResponse saveMogoLocation(@RequestBody LocationInfo locationInfo);

    /**
     * 更新专题
     * @param entity
     * @return
     */
    @RequestMapping(value = "/content/updateContentSubject",method = RequestMethod.POST)
    ObjectRestResponse<ContentInfo> updateContentSubject(@RequestBody ContentInfo entity);

    /**
     * 更新图集
     * @param entity
     * @return
     */
    @RequestMapping(value = "/content/updateContentAtlas",method = RequestMethod.POST)
    ObjectRestResponse<ContentInfo> updateContentAtlas(@RequestBody ContentInfo entity);

    /**
     * 查询图集
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/getContentAtlas",method = RequestMethod.GET)
    ObjectRestResponse<ContentInfo> getContentAtlas(@RequestParam("id") Integer id);

    /**
     * 查询活动
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/getContentActivity",method = RequestMethod.GET)
    ObjectRestResponse<ActivityInfo> getContentActivity(@RequestParam("id") Integer id);

    /**
     * 添加预览
     * @param entity
     * @return
     */
    @RequestMapping(value = "/contentPreview/addPreviewArticle",method = RequestMethod.POST)
    ObjectRestResponse<ContentPreviewInfo> addPreviewArticle(ContentPreviewInfo entity);

    /**
     * 搜索对应内容id的绑定数据
     * @param oldIds
     * @return
     */
    @RequestMapping(value = "/channelContent/getChannelContent",method = RequestMethod.GET)
    List<ChannelContentInfo> getChannelContent(@RequestParam("oldIds") List<Integer> oldIds,@RequestParam("channelId") Integer channelId);

    /**
     * 批量跟新内容排序
     * @param channelContentList
     * @return
     */
    @RequestMapping(value = "/channelContent/batchUpdate",method = RequestMethod.POST)
    Integer batchUpdate(@RequestBody List<ChannelContent> channelContentList);

    /**
     * 新增VR
     * @param entity
     * @return
     */
    @RequestMapping(value = "/content/addVRContent",method = RequestMethod.POST)
    ObjectRestResponse<VRContentAddInfo> addVRContent(@RequestBody VRContentAddInfo entity);

    /**
     * 得到VR详情
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/content/getVrContent",method = RequestMethod.GET)
    ObjectRestResponse<VRContentAddInfo> getVrContent(@RequestParam("contentId") Integer contentId);

    /**
     * 新增VR
     * @param entity
     * @return
     */
    @RequestMapping(value = "/content/updateVR",method = RequestMethod.POST)
    ObjectRestResponse<VRContentAddInfo> updateVR(@RequestBody VRContentAddInfo entity);


    /**
     * 分页VR查询
     * @param entity
     * @return
     */
    @RequestMapping(value = "/content/getVRPage",method = RequestMethod.POST)
    TableResultResponse<VideoContentSelectInfo> getVRPage(@RequestBody VideoSelectionInfo entity);

    /**
     * 保存子目录
     * @param subjectInfo
     * @return
     */
    @RequestMapping(value = "/specialCatalog/saveSpecialCatalog",method = RequestMethod.POST)
    ObjectRestResponse<Integer> saveSpecialCatalog(@RequestBody SubjectInfo subjectInfo);

    /**
     *解除子目录绑定
     * @param cid
     * @param targetId
     * @return
     */
    @RequestMapping(value = "/specialCatalog/deleteBind",method = RequestMethod.GET)
    ObjectRestResponse<Integer> deleteBind(@RequestParam("cid") Integer cid, @RequestParam("targetId") Integer targetId);

    /**
     * 删除子目录
     * @param cid
     * @param cataLogId
     * @return
     */
    @RequestMapping(value = "/specialCatalog/deleteSpecialCatalog",method = RequestMethod.GET)
    ObjectRestResponse<Integer> deleteSpecialCatalog(@RequestParam("cid") Integer cid, @RequestParam("cataLogId") Integer cataLogId);



    /**
     * 查询日志信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/getContentLog",method = RequestMethod.GET)
    List<ContentArticleLogInfo> getContentLog(@RequestParam("id") Integer id);

    /**
     * 模糊查询文章视频等
     * @param params
     * @return
     */
    @RequestMapping(value = "/content/getContentPageForMessage",method = RequestMethod.GET)
    TableResultResponse<ContentResponseInfo> getContentPageForMessage(@RequestParam Map<String,Object> params);

    @RequestMapping(value = "/content/getContentPageForBind",method = RequestMethod.POST)
    TableResultResponse<ContentOfSearchBindInfo> getContentPageForBind(@RequestBody ContentInfo entity);

    /**
     * 审核通过与驳回
     * @param content
     * @return
     */
    @RequestMapping(value = "/content/checkPassContent",method = RequestMethod.POST)
    ObjectRestResponse<Content> checkPassContent(@RequestBody ContentInfo content);

    /**
     * 我的内容 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/content/getMyContentPage", method = RequestMethod.POST)
    TableResultResponse<MyContentInfo> getMyContentPage(@RequestBody MyContentInfo entity);

    /** 批量删除
     * @param contentListInfo
     * @return
     */
    @RequestMapping(value = "/content/batchDeleteByList",method = RequestMethod.POST)
    ObjectRestResponse<Integer> batchDeleteByList(@RequestBody ContentListInfo contentListInfo);

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/channelContent/sortContent",method = RequestMethod.POST)
    ObjectRestResponse<Boolean> sortContent(@RequestBody Map<String, Object> params);

}
