package com.kanfa.news.admin.vo.video;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/4/4 14:32
 */
public class VideoTypeIdName implements Serializable {
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
