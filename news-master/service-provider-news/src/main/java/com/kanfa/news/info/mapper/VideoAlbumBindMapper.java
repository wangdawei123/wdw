package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.VideoAlbumBind;
import com.kanfa.news.info.vo.admin.video.VideoAlbumBindInfo;
import com.kanfa.news.info.vo.admin.video.VideoAlbumBindWebInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-02-26 18:01:42
 */
public interface VideoAlbumBindMapper extends Mapper<VideoAlbumBind> {


    //视频专辑列表中的关联内容的显示
    List<VideoAlbumBindInfo> getPage(Integer id);

    //关联内容里的 根据内容标题来搜索
    List<VideoAlbumBindInfo> getContentPage(@Param("videoAlbumId") Integer videoAlbumId,
                                            @Param("text")String text);

    //视频专辑分享页接口里所需的信息
    List<VideoAlbumBindWebInfo> getInfo(@Param("id")Integer id);


}
