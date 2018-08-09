package com.kanfa.news.app.mongoDB.entity;


import java.io.Serializable;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/4 17:37
 */
public class Check implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer uid;
    private  String name;
    private Integer time;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
