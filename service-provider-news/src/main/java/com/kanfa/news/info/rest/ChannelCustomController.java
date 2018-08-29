package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ChannelCustomBiz;
import com.kanfa.news.info.entity.ChannelCustom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("channelCustom")
public class ChannelCustomController extends BaseController<ChannelCustomBiz,ChannelCustom> {

}