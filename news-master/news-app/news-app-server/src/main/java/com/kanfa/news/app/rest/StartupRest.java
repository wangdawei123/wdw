package com.kanfa.news.app.rest;

import com.kanfa.news.app.biz.AdvBiz;
import com.kanfa.news.common.msg.AppObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("startup")
public class StartupRest extends BaseRest {


    @Autowired
    private AdvBiz advBiz;

    /**
     * 启动页开屏广告
     *
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public AppObjectResponse getStartUpAdv() {
        AppObjectResponse startUpAdv = advBiz.getStartUpAdv();
        return startUpAdv;
    }

}
