package com.kanfa.news.info.mongodb.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jiqc
 * @date 2018/2/27 19:07
 */
public class ChannelInfoOfLog implements Serializable {

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
