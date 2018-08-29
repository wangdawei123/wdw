package com.kanfa.news.app.biz;

import org.springframework.stereotype.Service;

import com.kanfa.news.app.entity.VideoTypeCustom;
import com.kanfa.news.app.mapper.VideoTypeCustomMapper;
import com.kanfa.news.common.biz.BaseBiz;

/**
 * 用户自定义的视频分类表
 *
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-08-16 16:18:54
 */
@Service
public class VideoTypeCustomBiz extends BaseBiz<VideoTypeCustomMapper,VideoTypeCustom> {
}