package com.kanfa.news.common.msg;

/**
 * @author Jezy
 */
public class AppObjectResponse<T> extends ObjectRestResponse<T> {
    private String version = "1.0.1";

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
