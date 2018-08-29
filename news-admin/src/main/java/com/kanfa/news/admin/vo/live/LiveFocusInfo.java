package com.kanfa.news.admin.vo.live;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/24 10:48
 */
public class LiveFocusInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;

    //标题
    private String title;
    //展示位置
    private String LiveType;
    //编辑人
    private String updateUser;
    //更新时间
    private Date updateTime;
    //是否发布
    private Integer isPublish;


    //判断条件
    //状态 1 正常 0 删除
    private Integer stat;
    //LiveTypeId 展示位置的ID 0代表是直播大厅
    private Integer liveTypeId;


    public Integer getLiveTypeId() {
        return liveTypeId;
    }

    public void setLiveTypeId(Integer liveTypeId) {
        this.liveTypeId = liveTypeId;
    }

    private Integer page;
    private Integer limit;

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

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
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

    public String getLiveType() {
        return LiveType;
    }

    public void setLiveType(String liveType) {
        LiveType = liveType;
    }

    public String getUpdateUser() {
        return  StringUtils.isEmpty(updateUser) ? "" : updateUser;
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

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }
}
