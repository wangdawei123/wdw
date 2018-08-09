package com.kanfa.news.admin.vo.activity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/12 11:00
 */
public class ActivityVotePeoplePageInfo implements Serializable {
    private Integer id;
    //名字 name
    private String name;
    //简介 summary
    private String summary;

    //排序 order_number
    private Integer orderNumber;
    //投票总数 vote_total
    private Integer voteTotal;
    //评论总数 comment_total
    private Integer commentTotal;
    //创建时间 create_time
    private Date createTime;

    //被投票人状态 status
    private Integer status;
    //投票对应的活动的ID
    private Integer activityVoteId;

    private Integer limit;
    private Integer page;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getVoteTotal() {
        return voteTotal;
    }

    public void setVoteTotal(Integer voteTotal) {
        this.voteTotal = voteTotal;
    }

    public Integer getCommentTotal() {
        return commentTotal;
    }

    public void setCommentTotal(Integer commentTotal) {
        this.commentTotal = commentTotal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getActivityVoteId() {
        return activityVoteId;
    }

    public void setActivityVoteId(Integer activityVoteId) {
        this.activityVoteId = activityVoteId;
    }
}
