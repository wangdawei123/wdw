package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.AdvertisementConfigBiz;
import com.kanfa.news.info.entity.AdvertisementConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jezy
 */
@Controller
@RequestMapping("advertisementConfig")
public class AdvertisementConfigController extends BaseController<AdvertisementConfigBiz, AdvertisementConfig> {
    public ObjectRestResponse findAll() {
        return this.findAll();
    }
}