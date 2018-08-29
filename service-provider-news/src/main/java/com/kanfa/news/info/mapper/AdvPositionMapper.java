package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.AdvPosition;
import tk.mybatis.mapper.common.Mapper;

/**
 * 广告位置
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-05-30 11:04:20
 */
public interface AdvPositionMapper extends Mapper<AdvPosition> {

    /**
     * 查询最大排序值
     * @return
     */
    Integer selectMaxOrderNumber();
}
