package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.ChannelContentCard;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 内容频道卡片类型绑定表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-23 20:01:17
 */
public interface ChannelContentCardMapper extends Mapper<ChannelContentCard> {
    Map<String,Object> selectByContentIdChannelId(@Param("contentId") Integer contentId,@Param("channelId") Integer channelId);
	
}
