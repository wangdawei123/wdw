package com.kanfa.news.app.vo.admin.info;

import java.io.Serializable;

/**
 * @author Jiqc
 * @date 2018/3/14 13:46
 */
public class CorpUserInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    //
    private Integer id;

    //用户名称
    private String name;

    //是否选中:  选中:1
    private Integer isSelect;

    //1在职 2 离职 3删除
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
