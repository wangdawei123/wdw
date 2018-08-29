package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.VideoSource;
import com.kanfa.news.info.vo.admin.video.VideoSourceInfo;
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
