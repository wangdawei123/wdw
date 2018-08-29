package com.kanfa.news.admin.vo.column;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/4/25 15:55
 */
public class ColumnNewShow implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private String image;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
