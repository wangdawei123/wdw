package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.AppDeviceBiz;
import com.kanfa.news.info.entity.AppDevice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("appDevice")
public class AppDeviceController extends BaseController<AppDeviceBiz,AppDevice> {

}