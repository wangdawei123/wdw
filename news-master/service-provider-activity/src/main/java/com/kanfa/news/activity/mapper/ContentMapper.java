package com.kanfa.news.activity.mapper;

import com.kanfa.news.activity.entity.Content;
import com.kanfa.news.activity.entity.ContentImageGroup;
import com.kanfa.news.activity.vo.info.ContentInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 内容表（含专题，文章，图集，视频类型）
 * 
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-05 14:23:28
 */
public interface ContentMapper extends Mapper<Content> {


    List<ContentInfo> selectByIds(List<Integer> list);

    List<ContentImageGroup> findImages(@Param("cid") Integer cid , @Param("pcount") Integer pcount);

}
