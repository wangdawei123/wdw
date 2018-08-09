package com.kanfa.news.common.constant.app;

/**
 * @author Jezy
 */

public enum APPCommonEnum {
    PLATFORM_IOS(1, "ios"),
    PLATFROM_ANDROID(2, "android"),

    SOURCE_IOS(3, "IOS流量源"),
    SOURCE_ANDROID(4,"android流量源"),

    PARAM_VALIDATE_FAIL(4002, "参数校验失败");

    private Integer key;
    private String value;

    APPCommonEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer key() {
        return key;
    }

    public String value() {
        return value;
    }
}
