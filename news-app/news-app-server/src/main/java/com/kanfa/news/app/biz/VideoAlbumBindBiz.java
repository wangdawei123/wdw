package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.VideoAlbumBind;
import com.kanfa.news.app.mapper.VideoAlbumBindMapper;
import com.kanfa.news.app.vo.admin.video.VideoAlbumBindInfo;
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
public class VideoAlbumBindBiz extends BaseBiz<VideoAlbumBindMapper,VideoAlbumBind> {
    @Autowired
    private VideoAlbumBindMapper videoAlbumBindMapper;

    public TableResultResponse<VideoAlbumBindInfo> getPage(Query query, Integer id){
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoAlbumBindInfo> list = videoAlbumBindMapper.getPage(id);
        return new TableResultResponse<VideoAlbumBindInfo>(result.getTotal(), list);
    }

    public TableResultResponse<VideoAlbumBindInfo> getContentPage(Query query,Integer videoAlbumId, String text){
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoAlbumBindInfo> searchlist = videoAlbumBindMapper.getContentPage(videoAlbumId,text);
        return new TableResultResponse<VideoAlbumBindInfo>(result.getTotal(), searchlist);
    }

}