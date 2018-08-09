package com.kanfa.news.admin.vo.activity;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/4/17 17:32
 */
public class ActivityBlueSkyCommentPageInfo implements Serializable {
    //id
    private Integer id;
    //评论人 nick
    private String nick;
    //评论对象 activityBlueSkyName
    private String activityBlueSkyName;
    //评论内容 comment
    private String comment;
    //是否通过审核 status 评论状态(1:未审核；2:已审核)
    private Integer status;

    //is_delete 是否已删除(0:未删除；1:已删除)
    private Integer isDelete;
    //page
    private Integer page;
    //limit
    private Integer limit;
    private Integer blueSkyPeopleId;

    public Integer getBlueSkyPeopleId() {
        return blueSkyPeopleId;
    }

    public void setBlueSkyPeopleId(Integer blueSkyPeopleId) {
        this.blueSkyPeopleId = blueSkyPeopleId;
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

    public String getActivityBlueSkyName() {
        return activityBlueSkyName;
    }

    public void setActivityBlueSkyName(String activityBlueSkyName) {
        this.activityBlueSkyName = activityBlueSkyName;
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
}
