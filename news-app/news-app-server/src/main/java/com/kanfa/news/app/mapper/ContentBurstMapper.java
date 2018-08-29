package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.ContentBurst;
import com.kanfa.news.app.vo.admin.info.ContentInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 新闻爆料与内容关系表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-22 13:46:14
 */
public interface ContentBurstMapper extends Mapper<ContentBurst> {

    List<ContentInfo> selectContentByBurst(@Param("entity") ContentBurst contentBurst);
}
