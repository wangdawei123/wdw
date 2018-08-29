package com.kanfa.news.info.vo.admin.kpicount;

import java.io.Serializable;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/7/31 18:32
 */
public class KpiCountListInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String wtype;
    private String name;
    private Integer uid;

    public String getWtype() {
        return wtype;
    }

    public void setWtype(String wtype) {
        this.wtype = wtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
