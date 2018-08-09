package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ContentPreview;
import com.kanfa.news.app.vo.admin.info.ContentPreviewInfo;
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
