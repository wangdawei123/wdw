package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.Channel;
import com.kanfa.news.app.mongoDB.entity.ChannelInfoOfLog;
import com.kanfa.news.app.vo.admin.info.ChannelAssociateContent;
import com.kanfa.news.app.vo.admin.info.ChannelInfo;
import com.kanfa.news.app.vo.admin.info.ContentInfo;
import com.kanfa.news.app.vo.admin.live.LiveInfo;
import com.kanfa.news.app.vo.admin.video.VideoChannelAddInfo;
import com.kanfa.news.app.vo.admin.video.VideoChannelInfo;
import com.kanfa.news.app.vo.admin.vr.VRChannelAddInfo;
import com.kanfa.news.app.vo.admin.vr.VRChannelInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 频道表
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-11 16:52:18
 */
public interface ChannelMapper extends Mapper<Channel> {

    /**
     * 查询频道集合
     * @return
     */
    List<ChannelInfo> selectChannlList(@Param("entity") Channel entity);


    List<LiveInfo> getKeywordDataType2(@Param("chanid") Integer chanid,
                                       @Param("offset") Integer offset,
                                       @Param("pcount") Integer pcount,
                                       @Param("keyword") String keyword,
                                       @Param("type") Integer type,
                                       @Param("pub") Integer pub);

    List<ContentInfo> getKeywordDataType(@Param("chanid") Integer chanid,
                                         @Param("offset") Integer offset,
                                         @Param("pcount") Integer pcount,
                                         @Param("keyword") String keyword,
                                         @Param("type") Integer type,
                                         @Param("pub") Integer pub);


    List<Channel> selectChannelIds(@Param("cate") Integer cate);

    /**
     * 添加频道返回id
     * @param entity
     * @return
     */
    int insertChannelSelective(Channel entity);

    /**
     * 查询五条资讯中绑定的内容
     * @param id
     * @return
     */
    List<ChannelAssociateContent> selectAssociateContentList(@Param("id") int id);

    /**
     * 查询视频管理的频道列表
     * @return
     */
    List<VideoChannelInfo> getPage();

    /**

     * 添加视频频道返回id
     * @param entity
     * @return
     */
    int insertVideoChannel(Channel entity);

    /**
     * 修改视频频道返回id
     * @param entity
     * @return
     */
    int updateVideoChannel(Channel entity);



    /**
     * 得到视频频道返回实体
     * @param id
     * @return
     */
    VideoChannelAddInfo selectVideoChannelByid(@Param("id") Integer id);




  /**
     * 根据Ids批量查询
     * @param oldIds
     * @return
     */
    List<Channel> getChannelByIds(@Param("oldIds") List<Integer> oldIds);

    /**
     * 批量更新
     * @param
     * @return
     */
    int batchUpdate(List<Channel> channelList);

    List<ChannelInfo> getChannelForContent(@Param("contentId") Integer contentId);

    /**
     * app频道查询 所有频道
     * @param cate
     * @return
     */
    List<Channel> appChannelCheck(@Param("cate") int cate);


    /**
     * 查询频道
     * @param entity
     * @return
     */
    List<ChannelInfo> getChannelCheck(@Param("entity") ChannelInfo entity);

    /**
     * 视频频道查询 所有频道
     * @param
     * @return
     */
    List<Channel> getVideoChannel();

    /**
     * VR管理-->频道管理的分页展示
     * @param
     * @return
     */
    List<VRChannelInfo> getAllVrChannel(VRChannelInfo entity);

    /**
     * VR管理-->频道管理的新增
     * @param
     * @return
     */
    int addVRChannel(VRChannelAddInfo entity);


    /**
     * 查询channel中排序值最高的
     * @param
     * @return
     */
    Integer selectMaxOrderNumber(@Param("category") Integer category);


    /**
     * VR管理-->频道管理的分页展示
     * @param
     * @return
     */
    List<VRChannelInfo> getPCChannel(VRChannelInfo entity);


    /**
     * VR管理-->频道管理的新增
     * @param
     * @return
     */
    int addPCChannel(VRChannelAddInfo entity);


    /**
     * VR视频列表左边的频道栏目
     * @param
     * @return
     */
    List<Channel> getVRLeftChannel();

    /**
     * pc咨询列表左边的频道栏目
     * @param
     * @return
     */
    List<Channel> getPCLeftChannel();

    /**
     * 批量查询频道
     * @param ids
     * @return
     */
    List<ChannelInfoOfLog> getChannelByIdsForLog(@Param("ids") List<Integer> ids);


    /**
     * 咨询列表左边的频道栏目
     * @param
     * @return
     */
    List<Channel> getConsultLeftChannel();
}
