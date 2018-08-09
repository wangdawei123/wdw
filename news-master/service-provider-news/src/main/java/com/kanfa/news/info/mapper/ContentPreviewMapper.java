package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.ContentPreview;
import com.kanfa.news.info.vo.admin.info.ContentPreviewInfo;
import tk.mybatis.mapper.common.Mapper;

/**
 * 预览表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-29 17:05:11
 */
public interface ContentPreviewMapper extends Mapper<ContentPreview> {

    /**
     * 新增
     * @param entity
     * @return
     */
    ContentPreviewInfo addPreviewArticle(ContentPreviewInfo entity);
}
