package com.kanfa.news.app.constant;

/**
 * @author jezy
 */

public enum ADEnum {
    AD_TYPE_ORG(2, "原生广告类型"),
    AD_TYPE_VIDEO(3, "视频类型"),
    AD_TYPE_BANNER(1, "横幅banner"),
    CARD_TYPE_AD_IMAGE_ONE(2, "单大图模式,用文章大图模式渲染，不用原有广告大图处理"),
    CARD_TYPE_AD_IMAGE_THREE(3, "三图模式"),
    CARD_TYPE_AD_VIDEO(5, "视频展示类型"),
    AD_KANFA_TYPE(16, "广告跳转类型");
    private Integer key;
    private String value;

    ADEnum(Integer key, String value) {
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
