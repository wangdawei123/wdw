package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.Content;
import com.kanfa.news.info.vo.admin.info.ActivityInfo;
import com.kanfa.news.info.vo.admin.info.ContentImageInfo;
import com.kanfa.news.info.vo.admin.info.ContentInfo;
import com.kanfa.news.info.vo.admin.live.LiveVideoBindInfo;
import com.kanfa.news.info.vo.admin.my.MyContentInfo;
import com.kanfa.news.info.vo.admin.video.RecycleBinInfo;
import com.kanfa.news.info.vo.admin.video.VideoContentInfo;
import com.kanfa.news.info.vo.admin.video.VideoContentSelectInfo;
import com.kanfa.news.info.vo.admin.video.VideoSelectionInfo;
import com.kanfa.news.info.vo.admin.vr.VRContentAddInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 内容表（含专题，文章，图集，视频类型）
 * 
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-05 14:23:28
 */
public interface ContentMapper extends Mapper<Content> {

    /**
     * 分页查询
     * @return
     */
    List<ContentInfo> getPage(@Param("entity") ContentInfo entity);

    /**
     * 内容表和内容详情表一起查询
     * @return
     */
    ContentInfo getContentArticle(@Param("id") Integer id);


    List<ContentInfo> hotgetlist(@Param("contentType") Integer contentType);

    /**
     * 更新浏览量
     * @param entity
     */
    void updateByviews(@Param("entity") ContentInfo entity);

    /**
     * web页--模糊搜索
     * @param searchKey
     * @return
     */
    List<ContentInfo> webSearch(@Param("searchKey") String searchKey);

    /**
     * web页--模糊搜索--下拉页面异步加载
     * @param searchKey
     * @return
     */
    List<ContentImageInfo> searchLoad(  @Param("searchKey") String searchKey,
                                        @Param("offset") Integer offset ,
                                        @Param("limit") Integer limit);

    /**
     * 内容表和图集表一起查询
     * @return
     */
    List<ContentImageInfo> getContentImageById(@Param("id") Integer id);


    /**
     * 内容表和视频表一起查询
     * @return
     */
    ContentInfo getContentVideoById(@Param("id") Integer id);


    /**
     * 分页查询PC
     * @return
     */
    List<ContentImageInfo> getPagePC(@Param("entity") ContentImageInfo entity);

    /**
     * 分页查询PC+channel中间表
     * @return
     */
    List<ContentImageInfo> getContentAll(@Param("entity") ContentImageInfo entity);

    /**
     * 新增文章
     * @param entity
     * @return
     */
    int addContent(ContentInfo entity);

    /**
     *  新增视频
     * @param entity
     * @return
     */
    int addVideoContent(VideoContentInfo entity);

    /**
     *  查询已删除列表的分页显示
     * @param
     * @return List<RecycleBinInfo>
     */
    List<RecycleBinInfo>  selectDeletes();


    /**
     *  搜索已删除列表的分页显示
     * @param
     * @return List<RecycleBinInfo>
     */
    List<RecycleBinInfo>  selectSearchDeletes(@Param("title") String title);

    List<ContentInfo> getContentPageForVideo(@Param("entity") ContentInfo contentInfo);


    /**
     *  已删除列表的清空回车站
     * @param
     * @return
     */
     void deleteAll();

    int addContentSubject(ContentInfo entity);

    ContentInfo getContent(@Param("id") Integer id);


    /**
     *  视频列表的分页显示及查询
     * @param
     * @return
     */
    List<VideoContentSelectInfo> getVideoPage(@Param("entity")VideoSelectionInfo entity);

    /**
     *  修改视频
     * @param entity
     * @return
     */
    void updateVideoContent(VideoContentInfo entity);

    List<Map> getContentByIds(@Param("id") Integer id);

    List<LiveVideoBindInfo> selectConBindList(@Param("id") Integer id);

    List<LiveVideoBindInfo> selectConBindList2(@Param("id") Integer id);

    List<Map> getByIds(@Param("ids") ArrayList<Integer> ids);

    /**
     * 查询内容绑定的内容
     * @param entity
     * @return
     */
    List<ContentInfo> getBroadContent(@Param("entity") ContentInfo entity);

    ContentInfo getContentForBind(@Param("id") Integer id);

    List<Map> getSearchListByKeyWord(@Param("entity") Map entity);
    /**
     *  专题详情页
     * @param id
     * @return
     */
    List<Map> getContentSpecialDetailById(@Param("id") Integer id);
    /**
     *  视频详情页接口
     * @param id
     * @return
     */
    List<Map> getContentVideoDetailById(@Param("id") Integer id);

    /**
     *  爆料绑定内容列表
     * @param id
     * @return
     */
    List<Map> getBindNewsByBurstId(@Param("id") Integer id);

     /**
      *  查询审核
     * @param contentInfo
     * @return
     */
    List<ContentInfo> getContentNotCheckPage(@Param("entity") ContentInfo contentInfo);

    /**
     * 查询对应的内ids
     * @param ids
     * @return
     */
    List<ContentInfo> selectContentByIds(@Param("list") List<Integer> ids,@Param("checkStatus") Integer checkStatus);

    /**
     * 新增图集
     * @param entity
     * @return
     */
    int addContentAtlas(ContentInfo entity);

    /**
     * 新增活动
     * @param entity
     * @return
     */
    int addContentActivity(ActivityInfo entity);

    /**
     * 查询具体专题
     * @param id
     * @return
     */
    ContentInfo selectSubjectById(@Param("id") Integer id);

    int addContentActivity(@Param("entity") ContentInfo entity);

    List<ContentInfo> selectByIds(List<Integer> list);

    /**
     *  新增Vr
     * @param entity
     * @return
     */
    int addVRContent(VRContentAddInfo entity);

    /**
     *  编辑Vr
     * @param entity
     * @return
     */
    int updateVRContent(VRContentAddInfo entity);

    List<Content> getList(@Param("cidsKey") List<Integer> cidsKey);

    /**
     * 查询活动
     * @param id
     * @return
     */
    ContentInfo selectActivityById(@Param("id") Integer id);

    /**
     * 模糊查询（消息推送）
     * @param contentInfo
     * @return
     */
    List<ContentInfo> getContentPageForMessage(@Param("entity") ContentInfo contentInfo);

    /**
     * 搜索绑定子目录集合
     * @param entity
     * @return
     */
    List<ContentInfo> getContentPageForBind(@Param("entity") ContentInfo entity);

    /**
     * 批量查询内容，查询评论数量
     * @param contentids
     * @return
     */
    List<Content> selectCommentCountByIds(@Param("contentids") ArrayList<Integer> contentids);

    /**
     * 我的内容 分页及搜索
     * @param
     * @return
     */
    List<MyContentInfo> getMyContentPage(MyContentInfo entity);


    List<Integer> selectListId(@Param("start")Integer start, @Param("size")Integer size);
    /**
     * h5详情页
     * @param cid
     * @return
     */
    ContentInfo getNewIndex(@Param("cid") Integer cid);
}
