package com.kanfa.news.common.constant.news;

/**
 * 部门Enum
 *
 * @author Jezy
 */
public enum BurstEnum {
    MD5_EEROR(1001, "md5值错误"),
    VIDEO_EXISTENCE(1002, "视频已经存在,请重新选择视频！"),
    BURST_ID_NOT_NULL(1003, "ID不能为空");

    private Integer key;
    private String value;

    BurstEnum(Integer key, String value) {
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
