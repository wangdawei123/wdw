package com.kanfa.news.admin.vo.column;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/25 15:20
 */
public class ColumnNewPageInfo implements Serializable {
    private Integer id;
    //栏目名称 name
    private String name;
    //描述 description
    private String description;
    //更新时间 update_time
    private Date updateTime;
    //操作人 update_uid
    private String updateUser;
    //是否隐藏 (0:不隐藏;1:隐藏) hide
    private Integer hide;
    //是否删除 is_delete 删除(0:正常;1:删除)
    private Integer isDelete;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return StringUtils.isEmpty(description) ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return StringUtils.isEmpty(updateUser) ? "" : updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getHide() {
        return hide;
    }

    public void setHide(Integer hide) {
        this.hide = hide;
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
