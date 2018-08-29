package com.kanfa.news.info.vo.admin.video;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/6/14 15:40
 */
public class ContentBroadcastBindWebInfo  implements Serializable{

    //标题
    private String title;
    //绑定内容id
    private Integer bind_id;
    //创建时间
    private String create_time;
    //类型
    private Integer type;
    //缩略图
    private String thumbimg;
    //链接地址
    private String url;
    //唤起appurl
    private String appUrl;
    //时长
    private String duration;
    //id
    private  Integer id;

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

    //
    private String image;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //来源作者名字
    private String source_name;
    //来源作者头像
    private  String source_img;

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getSource_img() {
        return source_img;
    }

    public void setSource_img(String source_img) {
        this.source_img = source_img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBind_id() {
        return bind_id;
    }

    public void setBind_id(Integer bind_id) {
        this.bind_id = bind_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getThumbimg() {
        return thumbimg;
    }

    public void setThumbimg(String thumbimg) {
        this.thumbimg = thumbimg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
