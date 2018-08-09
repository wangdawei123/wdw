package com.kanfa.news.data.biz;

import org.springframework.stereotype.Service;

import com.kanfa.news.data.entity.XmComment;
import com.kanfa.news.data.mapper.XmCommentMapper;
import com.kanfa.news.common.biz.BaseBiz;

/**
 * 评论表
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 20:50:52
 */
@Service
public class XmCommentBiz extends BaseBiz<XmCommentMapper,XmComment> {
}