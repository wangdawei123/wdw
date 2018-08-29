package com.kanfa.news.app.vo.app;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/28 10:52
 */
public class LiveVideoBindInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer cid;

    private Integer sort;
    private Integer sorttime;
    private Integer bindId;
    private Integer views;
    private String thumbimg;
    private Integer live_stat;
    private String duration;
    private Integer liveTypeId;
    private Integer type;
    private Integer createUid;
    private Date createTime;
    private Integer updateUid;
    private String url;




    //标题
    private String title;
    //类别 同用 from_type  1：content表视频，2：live表直播
    private Integer fromType;
    private String fromTypeCh;


    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }

    public String getFromTypeCh() {
        return fromTypeCh;
    }

    public void setFromTypeCh(String fromTypeCh) {
        this.fromTypeCh = fromTypeCh;
    }

    //最后编辑
    private String updateUser;
    //更新时间
    private Date updateTime;

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

    public String getUpdateUser() {
        return updateUser;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSorttime() {
        return sorttime;
    }

    public void setSorttime(Integer sorttime) {
        this.sorttime = sorttime;
    }

    public Integer getBindId() {
        return bindId;
    }

    public void setBindId(Integer bindId) {
        this.bindId = bindId;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getLive_stat() {
        return live_stat;
    }

    public void setLive_stat(Integer live_stat) {
        this.live_stat = live_stat;
    }

    public Integer getLiveTypeId() {
        return liveTypeId;
    }

    public void setLiveTypeId(Integer liveTypeId) {
        this.liveTypeId = liveTypeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getThumbimg() {
        return thumbimg;
    }

    public void setThumbimg(String thumbimg) {
        this.thumbimg = thumbimg;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(Integer updateUid) {
        this.updateUid = updateUid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
