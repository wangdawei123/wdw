package com.kanfa.news.info.vo.admin.activity;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/6/14 20:03
 */
public class ActivityWeb implements Serializable {
    private String ico;
    private String title;

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
