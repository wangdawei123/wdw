package com.kanfa.news.admin.vo.activity;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/4/13 13:53
 */
public class ActivityVotePeopleCommentPageInfo implements Serializable {
    private Integer id;
    //评论人
    private String nick;
    //评论对象
    private String activityVotePeopleName;
    //评论内容
    private String comment;
    //是否已审核 (1:未审核；2:已审核)
    private Integer status;


    //筛选条件
    private Integer activityVotePeopleId;
    private Integer isDelete;
    private Integer page;
    private Integer limit;
    public Integer getActivityVotePeopleId() {
        return activityVotePeopleId;
    }

    public void setActivityVotePeopleId(Integer activityVotePeopleId) {
        this.activityVotePeopleId = activityVotePeopleId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
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

    public String getActivityVotePeopleName() {
        return activityVotePeopleName;
    }

    public void setActivityVotePeopleName(String activityVotePeopleName) {
        this.activityVotePeopleName = activityVotePeopleName;
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
