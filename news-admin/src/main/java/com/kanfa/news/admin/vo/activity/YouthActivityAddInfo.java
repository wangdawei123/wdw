package com.kanfa.news.admin.vo.activity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/16 16:40
 */
public class YouthActivityAddInfo implements Serializable {

    //id
    private Integer id;
    //标题 title
    private String title;
    //banner图 image
    private String image;
    //开始时间 start_time
    private Date startTime;
    //结束时间 end_time
    private Date endTime;
    //活动状态
    private Integer status;
    //日榜规则 dayrule
    private String dayRule;
    //总榜规则 rule
    private String rule;
    //是否需要登录 need_login
    private Integer needLogin;
    //再次投票时间间隔(天) vote_period
    private Integer votePeriod;

    private Integer createdUid;
    private Date createdTime;
    private Integer isDelete;


    //条件 5 青春微普法大赛
    private Integer type;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDayRule() {
        return dayRule;
    }

    public void setDayRule(String dayRule) {
        this.dayRule = dayRule;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Integer getNeedLogin() {
        return needLogin;
    }

    public void setNeedLogin(Integer needLogin) {
        this.needLogin = needLogin;
    }

    public Integer getVotePeriod() {
        return votePeriod;
    }

    public void setVotePeriod(Integer votePeriod) {
        this.votePeriod = votePeriod;
    }

    public Integer getCreatedUid() {
        return createdUid;
    }

    public void setCreatedUid(Integer createdUid) {
        this.createdUid = createdUid;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
