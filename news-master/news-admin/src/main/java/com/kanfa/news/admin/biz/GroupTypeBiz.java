package com.kanfa.news.admin.biz;

import com.kanfa.news.admin.entity.GroupType;
import com.kanfa.news.admin.mapper.GroupTypeMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-12 8:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GroupTypeBiz extends BaseBiz<GroupTypeMapper,GroupType> {
}
