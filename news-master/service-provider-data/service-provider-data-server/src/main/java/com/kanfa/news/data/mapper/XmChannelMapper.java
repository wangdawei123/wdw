package com.kanfa.news.data.mapper;

import com.kanfa.news.data.common.vo.channel.ChannelInfo;
import com.kanfa.news.data.entity.XmChannel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 频道表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 11:36:00
 */
public interface XmChannelMapper extends Mapper<XmChannel> {

    List<ChannelInfo> selectListSelected(@Param("contentId") Integer id);
}
