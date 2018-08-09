package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.CommentStandard;
import com.kanfa.news.app.vo.admin.comment.CommentStandardInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-23 20:01:17
 */
public interface CommentStandardMapper extends Mapper<CommentStandard> {

    List<CommentStandardInfo> getPage(Map<String, Object> params);

    /**
     * 分页查询根据标题
     * @param params
     * @return
     */
    List<CommentStandardInfo> getPageByTitle(Map<String, Object> params);
}
