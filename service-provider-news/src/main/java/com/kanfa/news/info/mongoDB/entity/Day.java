package com.kanfa.news.info.mongodb.entity;


import java.io.Serializable;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/4 17:37
 */
public class Day implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer t;
    private Integer pv_total;
    private Integer app_pv;
    private Integer play_total;
    private Integer play_app;

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

    public Integer getPv_total() {
        return pv_total;
    }

    public void setPv_total(Integer pv_total) {
        this.pv_total = pv_total;
    }

    public Integer getApp_pv() {
        return app_pv;
    }

    public void setApp_pv(Integer app_pv) {
        this.app_pv = app_pv;
    }

    public Integer getPlay_total() {
        return play_total;
    }

    public void setPlay_total(Integer play_total) {
        this.play_total = play_total;
    }

    public Integer getPlay_app() {
        return play_app;
    }

    public void setPlay_app(Integer play_app) {
        this.play_app = play_app;
    }
}
