package com.kanfa.news.data.mapper;

import com.kanfa.news.data.entity.XmVideoTagBind;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 标签与视频关联表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 11:44:16
 */
public interface XmVideoTagBindMapper extends Mapper<XmVideoTagBind> {

    //查找videoTagBind通过videoId
    List<XmVideoTagBind> seletByVideoId(@Param("videoId") Integer videoId);
	
}
