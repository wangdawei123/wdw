package com.kanfa.news.app.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@Data
public class RequestConfiguration {
    @Value("${app.request.header.pushId}")
    private String pushId;

    @Value("${app.request.header.APPKEY}")
    private String appKey;

    @Value("${app.request.header.VERSION}")
    private String version;

    @Value("${app.request.header.Platform}")
    private String platform;

    @Value("${app.request.header.devID}")
    private String devId;

    @Value("${app.request.header.IDFA}")
    private String idFa;

    @Value("${app.request.header.Lng}")
    private String lng;

    @Value("${app.request.header.Lat}")
    private String lat;

    @Value("${app.request.header.SIGN}")
    private String sign;

    @Value("${app.request.header.UserAgent}")
    private String userAgent;

    @Value("${app.request.header.referer}")
    private String referer;

    @Value("${app.request.header.connection}")
    private String connection;

    @Value("${app.request.header.proxyConnection}")
    private String proxyConnection;

    @Value("${app.request.header.uid}")
    private String uid;

    @Value("${app.request.header.ipAddr}")
    private String ipAddr;
}
