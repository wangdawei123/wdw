package com.kanfa.news.admin.vo.activity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chen
 * on 2018/4/17 15:21
 */
public class ActivityBlueSkyShow implements Serializable {
    private Integer id;
    //学校名称 name
    private String name;
    //宣言 description
    private String description;
    //简介 introduction
    private String introduction;
    //头像 headimage
    private String headimage;
    //视频地址 url
    private String url;
    //视频时长 duration
    private String duration;
    //视频封面图 videoimage
    private String videoimage;
    //图集 imageList
    private List<String> imageList;

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVideoimage() {
        return videoimage;
    }

    public void setVideoimage(String videoimage) {
        this.videoimage = videoimage;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
