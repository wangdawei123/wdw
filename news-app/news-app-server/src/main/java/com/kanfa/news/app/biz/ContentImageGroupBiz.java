package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.ContentImageGroup;
import com.kanfa.news.app.mapper.ContentImageGroupMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 图集的图片表。一对多
 *
 * @author ffy
 * @email fengfangyan@kanfanews.com
 * @date 2018-03-13 18:26:57
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContentImageGroupBiz extends BaseBiz<ContentImageGroupMapper,ContentImageGroup> {

    @Autowired
    private ContentImageGroupMapper contenImageGroupMapper;

    /**
     * 查询所有
     * @param
     * @return
     */
    public List<ContentImageGroup> selectImageGroupList() {
        List<ContentImageGroup> list = contenImageGroupMapper.selectImageGroupList();
        return list;
    }

    /**
     * 查询图片集合
     * @param contentImageGroup
     * @return
     */
    public List<ContentImageGroup> getImageGroupList(ContentImageGroup contentImageGroup) {
        return contenImageGroupMapper.getImageGroupList(contentImageGroup);
    }
}