package com.kanfa.news.admin.vo.my;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/24 16:52
 */
public class MyContentInfo implements Serializable {
    private Integer id;
    private String title;
    //审核状态 check_status 审核状态（0待审核，1审核通过，2审核不通过）
    private Integer checkStatus;
    private String updateUser;
    private Date updateTime;
    private Integer contentType;

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    //筛选
    //channelId
    private Integer channelId;
    //is_check 显示状态，0审核列不显示，1审核列表显示
    private Integer isCheck;
    //create_uid
    private Integer createUid;
    //content_state 状态，1：正常，0：删除
    private Integer contentState;
    //page
    private Integer page;
    //limit
    private Integer limit;

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

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getUpdateUser() {
         return StringUtils.isEmpty(updateUser) ? "" : updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public Integer getContentState() {
        return contentState;
    }

    public void setContentState(Integer contentState) {
        this.contentState = contentState;
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
