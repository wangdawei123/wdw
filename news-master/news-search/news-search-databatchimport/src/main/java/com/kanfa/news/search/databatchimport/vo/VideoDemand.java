package com.kanfa.news.search.databatchimport.vo;


/**
 * Created by Chen
 * on 2018/4/8 16:47
 */
public class VideoDemand implements java.io.Serializable {
    private Integer id;
    private String title;
    private String url;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
