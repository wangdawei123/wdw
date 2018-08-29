package com.kanfa.news.info.vo.admin.my;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/25 10:56
 */
public class MyDemandPageInfo implements Serializable {
    private Integer id;
    //视频标题 title
    private String title;
    //上传时间 create_time
    private Date createTime;
    //视频库状态  1：上传失败 2：上传完成 3：转码中 4：转码完成 status
    private Integer status;

    //stat 1：正常 0：失败
    private Integer stat;
    //page
    private Integer page;
    //limit
    private Integer limit;
    //create_uid
    private Integer createUid;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
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

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }
}
