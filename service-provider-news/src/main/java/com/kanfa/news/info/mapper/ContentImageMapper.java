package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.ContentImage;
import com.kanfa.news.info.vo.admin.video.VideoChannelInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 内容附表-图集
 * 
 * @author ffy
 * @email fengfangyan@kanfanews.com
 * @date 2018-03-13 18:26:57
 */
public interface ContentImageMapper extends Mapper<ContentImage> {
    /**
     * 查询内容图集列表
     * @return
     */
    List<ContentImage> selectContentImageList(ContentImage entity);

    Integer selectNumByContentId(@Param("contentId") Integer contentId);
}
