package com.kanfa.news.admin.vo.live;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/4/4 14:15
 */
public class LiveTypeIdName implements Serializable {
    private Integer id;
    private String name;

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
}
