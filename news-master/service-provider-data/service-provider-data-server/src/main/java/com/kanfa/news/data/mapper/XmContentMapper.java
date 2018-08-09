package com.kanfa.news.data.mapper;

import com.kanfa.news.data.entity.XmContent;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * 内容表（含专题，文章，图集，视频类型）
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-06 16:41:42
 */
public interface XmContentMapper extends Mapper<XmContent> {

    XmContent getContentInfo(@Param("id") int id);
}
