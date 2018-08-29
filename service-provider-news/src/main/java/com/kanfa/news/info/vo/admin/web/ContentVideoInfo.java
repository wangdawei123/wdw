package com.kanfa.news.info.vo.admin.web;

import java.io.Serializable;

/**
 * @author Jiqc
 * @date 2018/6/11 17:34
 */
public class ContentVideoInfo implements Serializable {
    private String video;
    private String video_img;

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideo_img() {
        return video_img;
    }

    public void setVideo_img(String video_img) {
        this.video_img = video_img;
    }
}
