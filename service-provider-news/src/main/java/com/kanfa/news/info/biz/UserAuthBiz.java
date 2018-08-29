package com.kanfa.news.info.biz;

import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.UserAuth;
import com.kanfa.news.info.mapper.UserAuthMapper;
import com.kanfa.news.common.biz.BaseBiz;

/**
 * 前台用户第三方授权信息
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-06-04 16:03:20
 */
@Service
public class UserAuthBiz extends BaseBiz<UserAuthMapper,UserAuth> {
}