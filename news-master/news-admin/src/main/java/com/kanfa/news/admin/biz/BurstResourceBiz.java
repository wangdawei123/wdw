package com.kanfa.news.admin.biz;

import com.kanfa.news.admin.entity.BurstResource;
import com.kanfa.news.admin.mapper.BurstResourceMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 新闻爆料图片&视频表
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 17:00:40
 */
@Service
public class BurstResourceBiz extends BaseBiz<BurstResourceMapper,BurstResource> {
}