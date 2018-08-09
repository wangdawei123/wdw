package com.kanfa.news.user.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.user.biz.UserDeviceBiz;
import com.kanfa.news.user.entity.UserDevice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("userDevice")
public class UserDeviceController extends BaseController<UserDeviceBiz,UserDevice> {

}