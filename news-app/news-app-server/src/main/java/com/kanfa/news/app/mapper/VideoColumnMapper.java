package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.VideoColumn;
import com.kanfa.news.app.entity.VideoColumnBind;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 视频栏目表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-07-30 16:34:09
 */
public interface VideoColumnMapper extends Mapper<VideoColumn> {

    List<VideoColumn> findData();

    VideoColumnBind findBindData(@Param("id") Integer id);

}
