package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.UserDeviceBiz;
import com.kanfa.news.info.entity.UserDevice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("userDevice")
public class UserDeviceController extends BaseController<UserDeviceBiz,UserDevice> {

}