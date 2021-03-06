package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.VideoType;
import com.kanfa.news.app.vo.admin.video.VideoTypeInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-02-26 18:01:42
 */
public interface VideoTypeMapper extends Mapper<VideoType> {
    /**
     * 分页查询视频类型数据
     *
     * @return
     */
    List<VideoTypeInfo> getPage();
}
