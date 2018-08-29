package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.VideoColumn;
import com.kanfa.news.info.vo.admin.video.VideoColumnPageInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 视频栏目表
 * 
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-08-15 10:50:52
 */
public interface VideoColumnMapper extends Mapper<VideoColumn> {

    List<VideoColumnPageInfo> selectVideoColumns(@Param("entity")VideoColumnPageInfo entity);
	
}
