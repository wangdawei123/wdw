package com.kanfa.news.common.constant.news;

public enum CorpJobEnum {
    NAME_NOT_NULL(1001, "名称不能为空"),
    NAME_EXISTENCE(1003, "名称已存在"),
    CORP_JOB_STA_NORMAL(1, "正常"),
    CORP_JOB_STA_DISABLE(2, "停用"),
    CORP_JOB_STA_DELETED(3, "删除");

    private Integer key;
    private String value;

    CorpJobEnum(Integer key, String value) {
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
