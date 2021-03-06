package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.CommentBlacklist;
import com.kanfa.news.app.vo.admin.comment.CommentBlacklistInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 评论的屏蔽用户
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-28 13:30:44
 */
public interface CommentBlacklistMapper extends Mapper<CommentBlacklist> {

    /**
     * 查询所有黑名单
     * @return
     */
    List<CommentBlacklistInfo> getBlackUserPage();
}
