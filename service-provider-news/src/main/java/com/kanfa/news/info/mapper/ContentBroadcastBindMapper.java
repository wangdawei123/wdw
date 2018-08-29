package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.ContentBroadcastBind;
import com.kanfa.news.info.vo.admin.info.ContentInfo;
import com.kanfa.news.info.vo.admin.video.ContentBroadcastBindInfo;
import com.kanfa.news.info.vo.admin.web.ContentDetailBindInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 关联内容表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 17:46:08
 */
public interface ContentBroadcastBindMapper extends Mapper<ContentBroadcastBind> {

    List<ContentBroadcastBindInfo> getPage(@Param("contentId") Integer contentId);

    List<ContentBroadcastBindInfo> getSearchPage(@Param("contentId") Integer contentId,
                                                 @Param("title") String title);

    Integer getMaxOrderNumber();

    /**
     * 查询已经绑定的内容
     *
     * @param contentBroadcastBind
     * @return
     */
    List<ContentBroadcastBindInfo> getContentBind(@Param("contentBroadcastBind") ContentBroadcastBind contentBroadcastBind);

    List<Map> getLvshiBroadcastLivePage(@Param("channel_id") Integer channel_id, @Param("type") Integer type);

    List<Map> getLvshiBroadcastContentLivePage(@Param("channel_id") Integer channel_id);

    /**
     * 查询绑定内容
     * @param ids
     * @param contentId
     * @return
     */
    List<ContentBroadcastBind> getBroadcastBind(@Param("ids") List<Integer> ids,@Param("contentId") Integer contentId);

    /**
     * 视频分享查询绑定内容
     * @param id
     * @param
     * @return ids
     */
    List<Integer>  getBroadcastBindBindIds(@Param("id") Integer id);

    List<ContentInfo> getContentBroadBindByCid(@Param("contentId") Integer contentId);

    List<ContentInfo> getContentBindShareList(@Param("contentId") Integer cid);
}