package com.kanfa.news.common.constant;

/**
 * @author Jezy
 */

public enum ExceptionNum {
    WRONG_PERMISSONS("无用户权限");

    private final String value;

    String getValue() {
        return value;
    }

    ExceptionNum(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
