package com.kanfa.news.admin.vo.activity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/11 14:33
 */
public class VoteActivityAddShow implements Serializable {
    private Integer id;
    //标题
    private String title;
    //banner图 image
    private String image;
    //开始时间 start_time
    private Date startTime;
    //结束时间 end_time
    private Date endTime;
    //活动状态 status
    private Integer status;
    //是否需要登录 need_login
    private Integer needLogin;
    //是否允许多选 vote_multi
    private Integer voteMulti;
    //是否只能投一次 vote_once
    private Integer voteOnce;
    //再次投票时间间隔(天)vote_period
    private Integer votePeriod;
    //是否显示数量show_num
    private Integer showNum;
    //是否凌晨清空投票记数 flush_vote_num
    private Integer flushVoteNum;
    //活动详情 content
    private String content;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Integer getNeedLogin() {
        return needLogin;
    }

    public void setNeedLogin(Integer needLogin) {
        this.needLogin = needLogin;
    }

    public Integer getVoteMulti() {
        return voteMulti;
    }

    public void setVoteMulti(Integer voteMulti) {
        this.voteMulti = voteMulti;
    }

    public Integer getVoteOnce() {
        return voteOnce;
    }

    public void setVoteOnce(Integer voteOnce) {
        this.voteOnce = voteOnce;
    }

    public Integer getVotePeriod() {
        return votePeriod;
    }

    public void setVotePeriod(Integer votePeriod) {
        this.votePeriod = votePeriod;
    }

    public Integer getShowNum() {
        return showNum;
    }

    public void setShowNum(Integer showNum) {
        this.showNum = showNum;
    }

    public Integer getFlushVoteNum() {
        return flushVoteNum;
    }

    public void setFlushVoteNum(Integer flushVoteNum) {
        this.flushVoteNum = flushVoteNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
