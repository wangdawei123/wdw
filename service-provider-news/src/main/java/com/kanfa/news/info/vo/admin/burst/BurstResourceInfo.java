package com.kanfa.news.info.vo.admin.burst;

import java.io.Serializable;

/**
 * @author Jiqc
 * @date 2018/3/22 11:17
 */
public class BurstResourceInfo implements Serializable{

    private Integer id;

    //类型(1:图片;2:视频;3:文件)
    private Integer type;

    //视频/文件 title
    private String title;

    //上传地址
    private String url;

    //上传文件的md5值
    private String videomd;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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

    public String getVideomd() {
        return videomd;
    }

    public void setVideomd(String videomd) {
        this.videomd = videomd;
    }
}
