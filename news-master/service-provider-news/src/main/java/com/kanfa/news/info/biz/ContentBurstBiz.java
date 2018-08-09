package com.kanfa.news.info.biz;

import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.info.entity.ContentBurst;
import com.kanfa.news.info.mapper.ContentBurstMapper;
import com.kanfa.news.info.vo.admin.info.ContentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 新闻爆料与内容关系表
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-22 13:46:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContentBurstBiz extends BaseBiz<ContentBurstMapper,ContentBurst> {

    @Autowired
    private ContentBurstMapper contentBurstMapper;

    /**
     * 爆料关联内容
     * @param contentBurst
     * @return
     */
    public List<ContentInfo> selectContentByBurst(ContentBurst contentBurst) {
        return contentBurstMapper.selectContentByBurst(contentBurst);
    }
}