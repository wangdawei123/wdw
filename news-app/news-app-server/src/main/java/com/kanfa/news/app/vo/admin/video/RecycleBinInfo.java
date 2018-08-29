package com.kanfa.news.app.vo.admin.video;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/15 17:48
 *
 * 回收站
 */
public class RecycleBinInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    //内容Id
    private Integer id;
    //标题 title
    private String title;
    //类别 content_type
    private String contentType;
    //删除人delete_uid
    private String deleteUser;
    //删除日期delete_time
    private Date deleteTime;
    //最后更新时间update_time
    private Date updateTime;
    //浏览量view_count
    private  Integer viewCount;

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

    public String getContentType() {
        return StringUtils.isEmpty(contentType) ? "" : contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDeleteUser() {
        return StringUtils.isEmpty(deleteUser) ? "" : deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
}
