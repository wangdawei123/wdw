package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.Search;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-17 14:43:14
 */
public interface SearchMapper extends Mapper<Search> {
    List<Search> getSearchKeyword();
}
