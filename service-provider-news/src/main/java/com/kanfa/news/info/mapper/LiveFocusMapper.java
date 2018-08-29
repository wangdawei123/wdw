package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.LiveFocus;
import com.kanfa.news.info.vo.admin.live.LiveFocusInfo;
import com.kanfa.news.info.vo.admin.live.LiveInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 直播焦点图表
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-24 11:23:12
 */
public interface LiveFocusMapper extends Mapper<LiveFocus> {

    List<LiveInfo> selectFocus(LiveInfo entity);

    //分页显示及内容搜索
    List<LiveFocusInfo> getPage(LiveFocusInfo entity);


}
