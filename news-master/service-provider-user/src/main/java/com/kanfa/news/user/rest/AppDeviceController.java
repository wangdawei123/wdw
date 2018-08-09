package com.kanfa.news.user.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.user.biz.AppDeviceBiz;
import com.kanfa.news.user.entity.AppDevice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("appDevice")
public class AppDeviceController extends BaseController<AppDeviceBiz,AppDevice> {

}