package com.kanfa.news.user.mapper;

import com.kanfa.news.user.entity.Comment;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * 评论表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 20:01:59
 */
public interface CommentMapper extends Mapper<Comment> {

    List<Comment> selectComment(Comment comment);
}
