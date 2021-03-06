package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ChannelContent;
import com.kanfa.news.app.vo.admin.info.ChannelContentInfo;
import com.kanfa.news.app.vo.admin.info.ChannelInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 内容帮顶频道中间表
 * 
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 17:06:36
 */
public interface ChannelContentMapper extends Mapper<ChannelContent> {

    Integer updateChannelConent(@Param("channelContent") ChannelContent channelContent);

    List<ChannelInfo> selectListSelected(@Param("contentId") Integer id);


    //返回appChannelIdList
    List<Integer> selectAppChannels(@Param("contentId") Integer contentId);

    //返回pcChannelList
    List<Integer> selectPcChannels(@Param("contentId") Integer contentId);

    //返回vrChannelList
    List<Integer> selectVrChannels(@Param("contentId") Integer contentId);

    //直播中的返回appChannels
    List<Integer> getAppChannels(@Param("liveId") Integer liveId);

    List<Map> getPageContentListByChannelId(@Param("params") Map<String, Object> params);
   // 首页改版查询推荐内容
    List<Map> getNewPageContentListByChannelId(@Param("params") Map<String, Object> params);
    // 获取频道内容更新了几条
    List<Map> getNewContentCountByChannelId(@Param("params") Map<String, Object> params);

    // 新版视频内容列表查询
    List<Map> getNewVideoContentListByChannelId(@Param("params") Map<String, Object> params);


    /**
     * 获取频道内容绑定数据
     * @param ids
     * @return
     */
    List<ChannelContentInfo> getChannelContent(@Param("list") List<Integer> ids, @Param("channelId") Integer channelId);

    /**
     *统计审核数量
     * @param channelId
     * @param checkStatus
     * @return
     */
    Integer selectCountByChannelId(@Param("channelId") Integer channelId, @Param("checkStatus") Integer checkStatus);

    /**
     * 查询文章的绑定频道
     * @param id
     * @return
     */
    List<ChannelInfo> selectListForLog(@Param("contentId") Integer id);

    Integer updateContentDeleteByContentId(@Param("contentId") Integer contentId, @Param("fromType") Integer fromType);

    /**
     * 查询排序最大值
     * @return
     */
    Integer selectMaxOrderNumber();
}
