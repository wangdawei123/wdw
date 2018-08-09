package com.kanfa.news.info.vo.admin.info;

import java.io.Serializable;

/**
 * @author Jiqc
 * @date 2018/3/29 14:08
 */
public class ImageInfo implements Serializable{

    private Integer id;
    private String desc;
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
