package com.kanfa.news.info.biz;

import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.AdminUser;
import com.kanfa.news.info.mapper.AdminUserMapper;
import com.kanfa.news.common.biz.BaseBiz;

/**
 * 后台账号表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 20:02:21
 */
@Service
public class AdminUserBiz extends BaseBiz<AdminUserMapper,AdminUser> {
}