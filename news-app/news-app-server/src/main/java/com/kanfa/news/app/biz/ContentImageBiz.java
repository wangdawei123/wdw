package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.ContentImage;
import com.kanfa.news.app.mapper.ContentImageMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 内容附表-图集
 *
 * @author ffy
 * @email fengfangyan@kanfanews.com
 * @date 2018-03-13 18:26:57
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContentImageBiz extends BaseBiz<ContentImageMapper,ContentImage> {
    @Autowired
    private ContentImageMapper contenImageMapper;

    /**
     * 查询所有
     * @param entity
     * @return
     */
    public List<ContentImage> selectContentImageList(ContentImage entity) {
        List<ContentImage> list = contenImageMapper.selectContentImageList(entity);
        return list;
    }

}