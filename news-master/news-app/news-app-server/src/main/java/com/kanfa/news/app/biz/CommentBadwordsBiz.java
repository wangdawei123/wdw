package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.CommentBadwords;
import com.kanfa.news.app.mapper.CommentBadwordsMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.stereotype.Service;

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