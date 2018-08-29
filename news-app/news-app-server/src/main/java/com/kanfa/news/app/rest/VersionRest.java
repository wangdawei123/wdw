package com.kanfa.news.app.rest;

import com.kanfa.news.app.vo.user.VersionInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("version")
public class VersionRest extends BaseRest {
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ObjectRestResponse getAppVersion() {
        this.appObjectResponse.setData(new VersionInfo());
        return this.appObjectResponse;
    }
}
