package com.kanfa.news.app.rest;

import com.kanfa.news.app.config.RequestConfiguration;
import com.kanfa.news.app.vo.sys.AppHeader;
import com.kanfa.news.common.msg.AppObjectResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Jezy
 */
abstract class BaseRest {
    @Autowired
    protected HttpServletRequest request;

    private AppHeader appHeader;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    protected RequestConfiguration requestConfiguration;

    protected AppObjectResponse appObjectResponse;

    {
        appObjectResponse = new AppObjectResponse();
    }

    public Integer getUserId() {
        if (request == null) {
            return null;
        }
        String sessionId = this.request.getParameter("sessionid");
        if (StringUtils.isBlank(sessionId)) {
            return null;
        }
        Integer uid = (Integer) redisTemplate.opsForValue().get("APPUSER:SESSIONID:" + sessionId);
        return uid == null ? null : uid;
    }

    /**
     * 获取header头信息
     *
     * @return
     */
    public AppHeader getAppHeader() {
        if (this.request.getHeaderNames() == null) {
            return null;
        }
        appHeader = new AppHeader();
        appHeader.setLng(this.request.getHeader(requestConfiguration.getLng()));
        appHeader.setAppKey(this.request.getHeader(requestConfiguration.getAppKey()));
        appHeader.setDevId(this.request.getHeader(requestConfiguration.getDevId()));
        appHeader.setIdFa(this.request.getHeader(requestConfiguration.getIdFa()));
        appHeader.setPlatform(this.request.getHeader(requestConfiguration.getPlatform()));
        appHeader.setLat(this.request.getHeader(requestConfiguration.getLat()));
        appHeader.setSign(this.request.getHeader(requestConfiguration.getSign()));
        appHeader.setVersion(this.request.getHeader(requestConfiguration.getVersion()));
        appHeader.setPushId(this.request.getHeader(requestConfiguration.getPushId()));
        appHeader.setIpAddr(this.request.getHeader(requestConfiguration.getIpAddr()));
        return appHeader;
    }

}
