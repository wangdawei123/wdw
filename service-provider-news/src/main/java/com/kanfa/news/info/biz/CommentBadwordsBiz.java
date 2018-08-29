package com.kanfa.news.info.biz;

import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.CommentBadwords;
import com.kanfa.news.info.mapper.CommentBadwordsMapper;
import com.kanfa.news.common.biz.BaseBiz;

/**
 * 评论敏感字符
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-28 13:30:45
 */
@Service
public class CommentBadwordsBiz extends BaseBiz<CommentBadwordsMapper,CommentBadwords> {
}