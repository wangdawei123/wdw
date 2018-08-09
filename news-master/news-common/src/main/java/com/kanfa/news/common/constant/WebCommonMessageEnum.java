package com.kanfa.news.common.constant;

/**
 * Created by kanfa on 2018/4/23.
 */
public enum WebCommonMessageEnum {

    OK(200, "OK"),

    ACTIVITY_NOT_EXIST(4001,"活动不存在");

    private int key;
    private String value;

    WebCommonMessageEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int key() {
        return key;
    }

    public String value() {
        return value;
    }

}
