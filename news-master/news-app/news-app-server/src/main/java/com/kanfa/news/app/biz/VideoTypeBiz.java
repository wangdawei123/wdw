package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.VideoType;
import com.kanfa.news.app.mapper.VideoTypeMapper;
import com.kanfa.news.app.vo.admin.video.VideoTypeInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-02-26 18:01:42
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VideoTypeBiz extends BaseBiz<VideoTypeMapper,VideoType> {
    @Autowired
    private VideoTypeMapper videoTypeMapper;

    public TableResultResponse<VideoTypeInfo> page(Query query) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoTypeInfo> list = videoTypeMapper.getPage();
        return new TableResultResponse<VideoTypeInfo>(result.getTotal(), list);
    }
}