package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.VideoSource;
import com.kanfa.news.app.mapper.VideoSourceMapper;
import com.kanfa.news.app.vo.admin.video.VideoSourceInfo;
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
public class VideoSourceBiz extends BaseBiz<VideoSourceMapper,VideoSource> {
    @Autowired
    private VideoSourceMapper videoSourceMapper;

    public TableResultResponse<VideoSourceInfo> getPage(Query query) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoSourceInfo> list = videoSourceMapper.getPage();
        return new TableResultResponse<VideoSourceInfo>(result.getTotal(),list);
    }
}