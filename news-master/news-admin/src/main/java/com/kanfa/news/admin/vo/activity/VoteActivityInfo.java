package com.kanfa.news.admin.vo.activity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/11 16:36
 */
public class VoteActivityInfo implements Serializable {
    private Integer id;
    private String title;
    private String createUser;
    private Date startTime;
    private Date endTime;
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private Integer pv;
    private Integer activityVoteId;


    private Integer isDelete;
    private Integer type;
    private Integer limit;
    private Integer page;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public Integer getActivityVoteId() {
        return activityVoteId;
    }

    public void setActivityVoteId(Integer activityVoteId) {
        this.activityVoteId = activityVoteId;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }
}
