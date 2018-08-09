package com.kanfa.news.info.biz;

import com.kanfa.news.info.vo.admin.info.ContentPreviewInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.ContentPreview;
import com.kanfa.news.info.mapper.ContentPreviewMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

/**
 * 预览表
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-29 17:05:11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContentPreviewBiz extends BaseBiz<ContentPreviewMapper,ContentPreview> {

    @Autowired
    private ContentPreviewMapper contentPreviewMapper;

    /**
     * 新增预览
     * @param entity
     * @return
     */
    public ContentPreviewInfo addPreviewArticle(ContentPreviewInfo entity) {
        return contentPreviewMapper.addPreviewArticle(entity);
    }
}