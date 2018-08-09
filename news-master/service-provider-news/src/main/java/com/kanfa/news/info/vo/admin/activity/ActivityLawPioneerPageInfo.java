package com.kanfa.news.info.vo.admin.activity;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/18 17:25
 */
public class ActivityLawPioneerPageInfo implements Serializable {
    private Integer id;

    //名称
    private String title;
    //简介 description
    private String description;
    //排序值
    private Integer sort;
    //create_time
    private Date createTime;
    //page
    private Integer page;
    //limit
    private Integer limit;
    //is_delete
    private Integer isDelete;

    //activity_law_id
    private Integer activityLawId;

    public Integer getActivityLawId() {
        return activityLawId;
    }

    public void setActivityLawId(Integer activityLawId) {
        this.activityLawId = activityLawId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
