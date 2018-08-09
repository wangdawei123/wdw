package com.kanfa.news.web.rpc.app;

import com.kanfa.news.web.feign.IContentServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by kanfa on 2018/6/15.
 */

@Controller
@RequestMapping("web/weixin")
public class WeiXinRest {

    @Autowired
    private IContentServiceFeign contentServiceFeign;

    @ResponseBody
    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    public Map<String, Object> jump(@RequestParam(value = "url") String url) {
        return contentServiceFeign.getSignPackage(url);
    }



}
