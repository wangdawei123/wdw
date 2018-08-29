package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.entity.VideoColumn;
import com.kanfa.news.info.mapper.VideoColumnMapper;
import com.kanfa.news.info.vo.admin.video.VideoColumnAddInfo;
import com.kanfa.news.info.vo.admin.video.VideoColumnPageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 视频栏目表
 *
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-08-15 10:50:52
 */
@Service
public class VideoColumnBiz extends BaseBiz<VideoColumnMapper,VideoColumn> {

    @Autowired
    private VideoColumnMapper videoColumnMapper;


    public ObjectRestResponse addVideoColumn(VideoColumnAddInfo entity){
        VideoColumn videoColumn = new VideoColumn();
        BeanUtils.copyProperties(entity,videoColumn);
        videoColumnMapper.insertSelective(videoColumn);
        ObjectRestResponse<Object> objectObjectRestResponse = new ObjectRestResponse<>();
        objectObjectRestResponse.setRel(false);
        objectObjectRestResponse.setStatus(200);
        return objectObjectRestResponse;
    }


    /**
     * 分页查询视频/直播栏目列表
     * @param entity
     * @returnage
     */
    public TableResultResponse<VideoColumnPageInfo> getPage(VideoColumnPageInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        List<VideoColumnPageInfo> videoColumnPageInfos = videoColumnMapper.selectVideoColumns(entity);
        return new TableResultResponse<VideoColumnPageInfo>(result.getTotal(), videoColumnPageInfos);
    }

}