package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.vo.admin.video.VideoSourceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.VideoSource;
import com.kanfa.news.info.mapper.VideoSourceMapper;
import com.kanfa.news.common.biz.BaseBiz;
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
public class VideoSourceBiz extends BaseBiz<VideoSourceMapper,VideoSource> {
    @Autowired
    private VideoSourceMapper videoSourceMapper;

    public TableResultResponse<VideoSourceInfo> getPage(Query query) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoSourceInfo> list = videoSourceMapper.getPage();
        return new TableResultResponse<VideoSourceInfo>(result.getTotal(),list);
    }
}