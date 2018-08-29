package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.VideoAlbum;
import com.kanfa.news.info.vo.admin.video.VideoAlbumInfo;
import com.kanfa.news.info.vo.app.video.VideoAlbumAppInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-02-26 18:01:42
 */
public interface VideoAlbumMapper extends Mapper<VideoAlbum> {
    //分页查询视频专辑列表
    List<VideoAlbumInfo> getPage();

    //根据视频专辑的专题标题搜索
    List<VideoAlbumInfo> getSearchByTitle(String text);

    List<VideoAlbumAppInfo.VideoDate> getalbum(Integer id);

    List<Map> getVideoAlbum();

    //视频专辑分享页中的getOne
    VideoAlbum  getVideoAlbumById(@Param("id")Integer id);

    //是否已有推荐 album_type = 2
    Integer recommendedExist();

}
