package com.kanfa.news.admin.vo.activity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/19 14:39
 */
public class ActivityRaffleShow implements Serializable {
    private Integer id;
    private String title;
    private Date startTime;
    private Date endTime;
    private Integer status;
    private String buoy;
    private String rule;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBuoy() {
        return buoy;
    }

    public void setBuoy(String buoy) {
        this.buoy = buoy;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
