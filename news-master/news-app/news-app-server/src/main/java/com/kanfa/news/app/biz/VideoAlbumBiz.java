package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.VideoAlbum;
import com.kanfa.news.app.entity.VideoSource;
import com.kanfa.news.app.mapper.VideoAlbumMapper;
import com.kanfa.news.app.mapper.VideoSourceMapper;
import com.kanfa.news.app.vo.admin.video.VideoAlbumInfo;
import com.kanfa.news.app.vo.video.VideoAlbumAppInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-02-26 18:01:42
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VideoAlbumBiz extends BaseBiz<VideoAlbumMapper, VideoAlbum> {
    @Autowired
    private VideoAlbumMapper videoAlbumMapper;

    @Autowired
    private VideoSourceMapper videoSourceMapper;

    @Value("${app.video.album.shareUrl}")
    private String shareUrl;
    @Value("${app.video.album.showView}")
    private Integer showView;
    @Value("${app.video.album.shareStatus}")
    private Integer shareStatus;

    public TableResultResponse<VideoAlbumInfo> page(Query query) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoAlbumInfo> list = videoAlbumMapper.getPage();
        return new TableResultResponse<VideoAlbumInfo>(result.getTotal(), list);
    }

    public TableResultResponse<VideoAlbumInfo> searchPage(Query query, String text) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoAlbumInfo> searchlist = videoAlbumMapper.getSearchByTitle(text);
        return new TableResultResponse<VideoAlbumInfo>(result.getTotal(), searchlist);
    }

    public ObjectRestResponse<VideoAlbumAppInfo> getAlbum(Integer id, Integer userId, Integer albumType) {
        ObjectRestResponse<VideoAlbumAppInfo> objectRestResponse = new ObjectRestResponse<>();
        VideoAlbumAppInfo videoAlbumAppInfo = new VideoAlbumAppInfo();

        Example example = new Example(VideoAlbum.class);
        Example.Criteria criteria = example.createCriteria();
        if (albumType != null) {
            criteria.andEqualTo("album_type", 2);
        } else {
            criteria.andEqualTo("id", id);
        }
        List<VideoAlbum> videoAlbumList = videoAlbumMapper.selectByExample(example);
        if (videoAlbumList == null || videoAlbumList.size() <1) {
            return objectRestResponse;
        }
        if (albumType != null) {
            id = videoAlbumList.get(0).getId();
        }
        videoAlbumAppInfo.setVideo_data(videoAlbumMapper.getalbum(id));
        VideoSource videoSource = videoSourceMapper.selectByPrimaryKey(1);
        for (VideoAlbumAppInfo.VideoDate videoDate: videoAlbumAppInfo.getVideo_data()) {
            videoDate.setSource_name(StringUtils.isNotEmpty(videoDate.getSource_name()) ? videoDate.getSource_name() : videoSource.getName());
            videoDate.setSource_image(StringUtils.isNotEmpty(videoDate.getSource_image()) ? videoDate.getShare_img() : videoSource.getImage());
            videoDate.setIs_liked(0);
            videoDate.setShare("www.baidu.com");
            videoDate.setShare_img(StringUtils.isNotEmpty(videoDate.getShare_img()) ? videoDate.getShare_img() : shareUrl);
            videoDate.setShow_view(showView);
        }
        videoAlbumAppInfo.setTitle(videoAlbumList.get(0).getTitle());
        videoAlbumAppInfo.setShare_status(shareStatus);
        videoAlbumAppInfo.getShare().setTitle(videoAlbumAppInfo.getTitle());
        videoAlbumAppInfo.getShare().setSubtitle(StringUtils.isNotEmpty(videoAlbumList.get(0).getSummary()) ? videoAlbumList.get(0).getSummary() : videoAlbumAppInfo.getTitle());
        videoAlbumAppInfo.getShare().setUrl("www.baidu.com");
        videoAlbumAppInfo.getShare().setImg(videoAlbumList.get(0).getCoverImg());
        objectRestResponse.setData(videoAlbumAppInfo);
        return objectRestResponse;
    }

}