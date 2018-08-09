package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.VideoTagBind;
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
public interface VideoTagBindMapper extends Mapper<VideoTagBind> {

    /**
     * 删除内容相关的标签绑定
     * @param id
     * @return
     */
    int deleteTagByCid(@Param("id") Integer id);

    //查找videoTagBind通过videoId
    List<VideoTagBind> seletByVideoId(@Param("videoId") Integer videoId);
}
