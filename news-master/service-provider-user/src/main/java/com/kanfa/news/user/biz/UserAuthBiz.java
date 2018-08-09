package com.kanfa.news.user.biz;

import com.kanfa.news.user.vo.admin.UserAuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.user.entity.UserAuth;
import com.kanfa.news.user.mapper.UserAuthMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.web.bind.annotation.RequestParam;

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

    public UserAuth getOneByweixPlatform(Integer platform,String unionid){
        UserAuth auth = new UserAuth();
        auth.setPlatform(platform);
        auth.setUnionid(unionid);
        auth.setStatus(1);
        return userAuthMapper.selectOne(auth);
    }

    public Integer updateOneByweixUnionid(Integer platform,String unionid ,String openid){
        UserAuth auth = new UserAuth();
        auth.setPlatform(platform);
        auth.setStatus(1);
        auth.setUnionid(unionid);
        UserAuth userAuth = userAuthMapper.selectOne(auth);
        if(userAuth != null){
            userAuth.setOpenid(openid);
            return userAuthMapper.updateByPrimaryKeySelective(userAuth);
        }else {
            return 0;
        }
    }



}