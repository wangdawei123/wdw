package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.AdminUserBiz;
import com.kanfa.news.info.entity.AdminUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("adminUser")
public class AdminUserController extends BaseController<AdminUserBiz,AdminUser> {

}