package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.entity.VideoTag;
import com.kanfa.news.info.mapper.VideoTagMapper;
import com.kanfa.news.info.vo.admin.video.VideoTagInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-02-26 18:01:42
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VideoTagBiz extends BaseBiz<VideoTagMapper, VideoTag> {

    @Autowired
    private VideoTagMapper videoTagMapper;

    public List<VideoTag> getTagListByConentId(Integer videoId) {
        return videoTagMapper.getTagListByConentId(videoId);
    }

    public TableResultResponse<VideoTagInfo> page(Query query) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoTagInfo> list = videoTagMapper.getPage();
        return new TableResultResponse<VideoTagInfo>(result.getTotal(), list);
    }


    public TableResultResponse<VideoTagInfo> searchPage(Query query, String text) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoTagInfo> searchlist = videoTagMapper.getSearchPage(text);
        return new TableResultResponse<VideoTagInfo>(result.getTotal(), searchlist);
    }

}