package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.UserAuth;
import com.kanfa.news.app.mapper.UserAuthMapper;
import com.kanfa.news.app.vo.admin.UserAuthInfo;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 前台用户第三方授权信息
 *
 * @author wdw
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 16:29:52
 */
@Service
public class UserAuthBiz extends BaseBiz<UserAuthMapper,UserAuth> {

    @Autowired
    private UserAuthMapper userAuthMapper;

    public UserAuth selecetByOpenId(UserAuth entity){
        UserAuth auth=userAuthMapper.selectOne(entity);
        return auth;
    }

    public void insertUser(UserAuthInfo user){
        userAuthMapper.insertUser(user);
    }

}