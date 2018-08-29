package com.kanfa.news.admin.vo.video;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/8/15 13:52
 */
public class VideoColumnZi implements Serializable {
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
