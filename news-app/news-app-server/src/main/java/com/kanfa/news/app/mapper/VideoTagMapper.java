package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.VideoTag;
import com.kanfa.news.app.vo.admin.video.VideoTagInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-02-26 18:01:42
 */
public interface VideoTagMapper extends Mapper<VideoTag> {

    /**
     * 根据对饮的内容id查询标签集合
     * @param videoId
     * @return
     */
    List<VideoTag> getTagListByConentId(@Param("videoId") Integer videoId);

    List<VideoTagInfo> getPage();

    List<VideoTagInfo> getSearchPage(String text);

    List<VideoTagInfo> selectTag(@Param("entity") VideoTagInfo videoTagInfo);

    int addTag(VideoTag tag);

}
