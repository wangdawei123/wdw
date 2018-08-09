package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.VideoSource;
import com.kanfa.news.app.vo.admin.video.VideoSourceInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-02-26 18:01:42
 */
public interface VideoSourceMapper extends Mapper<VideoSource> {
    //作者渠道列表的分页显示
    List<VideoSourceInfo> getPage();
}
