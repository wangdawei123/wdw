package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.Love;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 喜欢表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-06-14 10:54:39
 */
public interface LoveMapper extends Mapper<Love> {

    Love getLoveOne(@Param("love") Love love);

    List<Love> getLoveList(@Param("love") Love love);
}
