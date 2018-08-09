package com.kanfa.news.info.biz;

import com.kanfa.news.info.vo.admin.baseuser.AdminUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.BaseUser;
import com.kanfa.news.info.mapper.BaseUserMapper;
import com.kanfa.news.common.biz.BaseBiz;

import java.util.List;

/**
 * 
 *
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-05-16 13:53:49
 */
@Service
public class BaseUserBiz extends BaseBiz<BaseUserMapper,BaseUser> {

    @Autowired
    private BaseUserMapper baseUserMapper;

    public List<AdminUserInfo> getAllBaseUser(BaseUser baseUser) {
        return baseUserMapper.getAllBaseUser(baseUser);
    }
}