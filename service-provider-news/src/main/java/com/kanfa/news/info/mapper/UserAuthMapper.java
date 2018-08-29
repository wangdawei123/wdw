package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.UserAuth;
import tk.mybatis.mapper.common.Mapper;

/**
 * 前台用户第三方授权信息
 * 
 * @author wdw
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 16:29:52
 */
public interface UserAuthMapper extends Mapper<UserAuth> {

    void insertUser(UserAuth user);
}
