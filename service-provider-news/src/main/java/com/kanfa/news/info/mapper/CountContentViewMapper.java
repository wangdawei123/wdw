package com.kanfa.news.info.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/4 16:08
 */
public interface CountContentViewMapper {

    List<Map<String,Object>> listBaseInfoByCid(@Param("cid")Integer cid);

    List<Map<String,Object>> listAuthorInfoByCid(@Param("cid")Integer cid);

    List<Map<String,Object>> listAuthorInfoByCidFromKpiContent(@Param("cid")Integer cid);

    List<Map<String,Object>> listAuthorInfoByCidFromKpiVideo(@Param("cid")Integer cid);

    List<Map<String,Object>> listAuthorInfoByCidFromContentReporter(@Param("cid")Integer cid);

    List<Map<String,Object>> listChannelInfoByCid(@Param("cid")Integer cid);


}
