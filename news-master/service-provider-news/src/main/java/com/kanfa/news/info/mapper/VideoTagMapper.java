package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.VideoTag;
import org.apache.ibatis.annotations.Param;
import com.kanfa.news.info.vo.admin.video.VideoTagInfo;
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
