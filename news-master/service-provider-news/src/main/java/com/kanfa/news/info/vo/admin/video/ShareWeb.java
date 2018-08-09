package com.kanfa.news.info.vo.admin.video;

/**
 * Created by Chen
 * on 2018/6/14 20:06
 */
public class ShareWeb implements java.io.Serializable {
    private String title;
    private String img;
    private String subtile;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSubtile() {
        return subtile;
    }

    public void setSubtile(String subtile) {
        this.subtile = subtile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
