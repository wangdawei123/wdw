package com.kanfa.news.admin.vo.live;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/4/3 14:58
 */
public class CorpUserIdName implements Serializable {
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
