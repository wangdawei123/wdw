package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ContentBroadcast;
import com.kanfa.news.app.vo.admin.live.ContentBroadcastInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 节目直播表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 13:47:27
 */
public interface ContentBroadcastMapper extends Mapper<ContentBroadcast> {


    //aliyun列表的分页显示及搜索
    List<ContentBroadcastInfo> getPage(ContentBroadcastInfo entity);

    //查询最大值的
    int addContentBroadcast(ContentBroadcast entity);

    ContentBroadcast selectByCid(@Param("cid") Integer cid);
}
