package com.kanfa.news.info.rest;

import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.BaseUserBiz;
import com.kanfa.news.info.entity.BaseUser;
import com.kanfa.news.info.vo.admin.baseuser.AdminUserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("baseUser")
public class BaseUserController extends BaseController<BaseUserBiz,BaseUser> {

    /**
     * 获取编辑人（未删除）
     * @ret
     */
    @RequestMapping(value = "/getAllBaseUser",method = RequestMethod.GET)
    public List<AdminUserInfo> getAllBaseUser(){
        BaseUser baseUser=new BaseUser();
        baseUser.setStatus(NewsEnumsConsts.BaseUserOfStatus.OnPosition.key());
        return this.baseBiz.getAllBaseUser(baseUser);
    }

}