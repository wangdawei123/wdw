package com.kanfa.news.common.constant.news;

/**
 * 部门Enum
 *
 * @author Jezy
 */
public enum CorpDeptEnum {
    NAME_NOT_NULL(1001, "名称不能为空"),
    NAME_EXISTENCE(1003, "名称已存在"),
    CORP_DEPT_STA_NORMAL(1, "正常"),
    CORP_DEPT_STA_DISABLE(2, "停用"),
    DELETE(1002, "删除");

    private Integer key;
    private String value;

    CorpDeptEnum(Integer key, String value) {
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
