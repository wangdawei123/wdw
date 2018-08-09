package com.kanfa.news.admin.vo.activity;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/4/17 18:29
 */
public class ActivityBlueSkyCommentShow implements Serializable {
    private Integer id;
    //候选人姓名
    private String activityBlueSkyName;
    private String nick;
    private String comment;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityBlueSkyName() {
        return activityBlueSkyName;
    }

    public void setActivityBlueSkyName(String activityBlueSkyName) {
        this.activityBlueSkyName = activityBlueSkyName;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
