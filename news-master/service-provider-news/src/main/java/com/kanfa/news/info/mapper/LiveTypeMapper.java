package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.LiveType;
import com.kanfa.news.info.vo.admin.live.LiveTypeInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 直播类型表
 *
 * @param
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-26 13:26:20
 */
public interface LiveTypeMapper extends Mapper<LiveType> {
    /**
     * 分页查询直播类型数据
     *
     * @return
     */
    List<HashMap> getPage();


    List<LiveType> findLiveTypeList();

}
