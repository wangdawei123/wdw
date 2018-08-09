package com.kanfa.news.data.mapper;

import com.kanfa.news.data.entity.XmKpiCount;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-08-07 11:14:21
 */
public interface XmKpiCountMapper extends Mapper<XmKpiCount> {

    //通过type和typeId查询kpiCount
    List<XmKpiCount> selectXmKpiCounts(@Param("type")Integer type,
                                       @Param("typeId")Integer typeId);
	
}
