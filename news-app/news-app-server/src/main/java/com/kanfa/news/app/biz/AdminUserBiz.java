package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.AdminUser;
import com.kanfa.news.app.mapper.AdminUserMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.stereotype.Service;

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