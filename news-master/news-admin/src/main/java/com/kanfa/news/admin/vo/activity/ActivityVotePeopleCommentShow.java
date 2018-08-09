package com.kanfa.news.admin.vo.activity;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/4/13 14:52
 */
public class ActivityVotePeopleCommentShow implements Serializable {
    private Integer id;
    //候选人姓名
    private String activityVotePeopleName;
    private String nick;
    private String comment;
    private Integer status;


    public String getActivityVotePeopleName() {
        return activityVotePeopleName;
    }

    public void setActivityVotePeopleName(String activityVotePeopleName) {
        this.activityVotePeopleName = activityVotePeopleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
