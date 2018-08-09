package com.kanfa.news.user.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
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





}
