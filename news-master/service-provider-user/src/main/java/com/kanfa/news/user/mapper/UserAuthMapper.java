package com.kanfa.news.user.mapper;

import com.kanfa.news.user.entity.UserAuth;
import com.kanfa.news.user.vo.admin.UserAuthInfo;
import tk.mybatis.mapper.common.Mapper;

/**
 * 前台用户第三方授权信息
 * 
 * @author wdw
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 16:29:52
 */
public interface UserAuthMapper extends Mapper<UserAuth> {

    void insertUser(UserAuthInfo user);
}
