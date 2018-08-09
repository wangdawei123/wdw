package com.kanfa.news.data.mapper;

import com.kanfa.news.data.entity.XmActivityContentBind;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 19:46:01
 */
public interface XmActivityContentBindMapper extends Mapper<XmActivityContentBind> {

    List<Integer> getActityIdListByCid(@Param("cid") int id);
}
