package com.kanfa.news.info.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/7 16:43
 */
public interface HotGetListMapper {
    List<Map<String,Object>> list(@Param("type")Integer type,@Param("page")Integer page,@Param("limit")Integer limit,@Param("startDate") String startDate, @Param("endDate") String endDate);

    Map<String,Object> findImageNum(@Param("cid")Integer cid);

    Map<String,Object> findDuration(@Param("findDuration")Integer cid);
}
