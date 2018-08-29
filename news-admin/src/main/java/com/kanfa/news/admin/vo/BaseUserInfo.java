package com.kanfa.news.admin.vo;

/**
 * @author Jiqc
 * @date 2018/3/7 13:38
 */
public class BaseUserInfo {
    private Integer id;

    //用户名
    private String name;

    //是否选中
    private Integer isSelect;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Integer isSelect) {
        this.isSelect = isSelect;
    }
}
