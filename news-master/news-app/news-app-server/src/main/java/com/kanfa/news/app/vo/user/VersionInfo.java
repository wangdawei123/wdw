package com.kanfa.news.app.vo.user;

import org.springframework.beans.factory.annotation.Value;

/**
 * 版本信息
 *
 * @author Jezy
 */
public class VersionInfo {
    @Value("${app.version.force}")
    private String force;
    @Value("${app.version.msg}")
    private String msg;
    @Value("${app.version.url}")
    private String url;
    @Value("${app.version.version}")
    private String version;
}
