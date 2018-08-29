package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.User;
import com.kanfa.news.app.vo.video.UserAuthInfo;
import tk.mybatis.mapper.common.Mapper;

/**
 * 前台用户
 * 
 * @author wdw
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 16:29:52
 */
public interface UserMapper extends Mapper<User> {

    /**
     *当用户第一次登陆时，添加用户信息
     * @param user
     */
    void insertBaseUser(UserAuthInfo user);

    /**
     *
     * @param user
     * @return
     */
	int insertUser(User user);

}
