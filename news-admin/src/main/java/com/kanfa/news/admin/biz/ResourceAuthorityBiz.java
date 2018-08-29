package com.kanfa.news.admin.biz;

import com.kanfa.news.admin.entity.ResourceAuthority;
import com.kanfa.news.admin.mapper.ResourceAuthorityMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ace on 2017/6/19.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceAuthorityBiz extends BaseBiz<ResourceAuthorityMapper,ResourceAuthority> {
}
