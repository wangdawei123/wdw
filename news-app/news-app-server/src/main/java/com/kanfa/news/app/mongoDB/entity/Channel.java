package com.kanfa.news.app.mongoDB.entity;


import java.io.Serializable;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/4 17:37
 */
public class Channel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private  String name;

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
