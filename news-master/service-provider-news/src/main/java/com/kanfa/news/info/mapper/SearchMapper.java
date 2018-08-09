package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.Search;
import com.kanfa.news.info.vo.admin.search.SearchInfo;
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

    /**
     * 搜索关键字列表的分页
     * @param
     * @return
     */
    List<SearchInfo> getSearchPage(SearchInfo entity);
}
