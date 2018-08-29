package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ContentImage;
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
}
