package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.ExtendChannel;
import com.kanfa.news.info.vo.admin.activity.ExtendChannelPageInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 推广渠道
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-16 11:26:02
 */
public interface ExtendChannelMapper extends Mapper<ExtendChannel> {

    /**
     * 推广渠道的分页及查询
     * @return
     */
    List<ExtendChannelPageInfo> getExtendChannelPage(ExtendChannelPageInfo entity);

    /**
     * 推广渠道的集合id name
     * @return
     */
    List<ExtendChannel> extendChannelIdName();
}
