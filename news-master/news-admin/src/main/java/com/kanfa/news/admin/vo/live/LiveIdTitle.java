package com.kanfa.news.admin.vo.live;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/4/2 14:54
 */
public class LiveIdTitle implements Serializable {
    private Integer id;
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
