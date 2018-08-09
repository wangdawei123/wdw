package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.ChannelFocus;
import com.kanfa.news.info.vo.admin.info.ChannelFocusInfo;
import com.kanfa.news.info.vo.admin.video.VideoChannelFocusInfo;
import com.kanfa.news.info.vo.admin.video.VideoChannelFocusListInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 频道焦点图
 * 
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-07 09:57:46
 */
public interface ChannelFocusMapper extends Mapper<ChannelFocus> {



    List<ChannelFocusInfo> getPage(Map<String, Object> params);

    /**
     * 展示视频管理下下的 焦点图管理
     * @return
     */
    List<VideoChannelFocusInfo> getVideoChannelFocusPage();

    /**
     * 查询轮播图
     */
    List<ChannelFocusInfo> getFocusAll(ChannelFocusInfo entity);


    /**
     * 展示视频管理下下的-->焦点图-->管理焦点图
     * @return
     */
    List<VideoChannelFocusListInfo> getVideoFocusList(@Param("channelId") Integer channelId);

    /**
     * Vr管理-->焦点图管理
     * @return
     */
    List<VideoChannelFocusInfo> getVRChannelFocusPage();

    /**
     * Pc管理-->焦点图管理
     * @return
     */
    List<VideoChannelFocusInfo> getPcChannelFocusPage();

    /**
     * 查询焦点视图
     * @param ids
     * @return
     */
    List<ChannelFocus> getChannelFocusByIdsAndChannelId(@Param("ids") List<Integer> ids);

    /**
     * 获取最大排序值
     * @return
     */
    Integer getMaxOrderNumber();
}
