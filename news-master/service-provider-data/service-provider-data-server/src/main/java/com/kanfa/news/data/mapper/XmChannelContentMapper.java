package com.kanfa.news.data.mapper;

import com.kanfa.news.data.common.vo.channel.ChannelInfo;
import com.kanfa.news.data.entity.XmChannelContent;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 频道与内容关系表
 *
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-08-07 11:14:06
 */
public interface XmChannelContentMapper extends Mapper<XmChannelContent> {

    //直播中的返回appChannels
    List<Integer> getAppChannels(@Param("liveId")Integer liveId);

    //返回appChannelIdList
    List<Integer> selectAppChannels(@Param("contentId")Integer contentId );

    //返回pcChannelList
    List<Integer> selectPcChannels(@Param("contentId")Integer contentId );

    //返回vrChannelList
    List<Integer> selectVrChannels(@Param("contentId")Integer contentId );

    //vr返回selectAppChannelsForVr
    List<Integer> selectAppChannelsForVr(@Param("contentId")Integer contentId );

    //通过cid查询集合
    List<XmChannelContent> selectChannelContentByCid(@Param("cid")Integer cid);
    /**
     * 频道绑定查询
     * @param xmChannelContent
     * @return
     */
    List<ChannelInfo> getChannelContentChannelId(@Param("xmChannelContent") XmChannelContent xmChannelContent);
}
