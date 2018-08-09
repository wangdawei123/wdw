package com.kanfa.news.app.biz;

import org.springframework.stereotype.Service;

import com.kanfa.news.app.entity.VideoColumnBind;
import com.kanfa.news.app.mapper.VideoColumnBindMapper;
import com.kanfa.news.common.biz.BaseBiz;

/**
 * 栏目和内容关联表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-07-30 16:34:09
 */
@Service
public class VideoColumnBindBiz extends BaseBiz<VideoColumnBindMapper,VideoColumnBind> {
}