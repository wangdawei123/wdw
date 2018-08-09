package com.kanfa.news.app.vo.admin.video;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/23 11:16
 */
public class ContentBroadcastBindInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    //标题
    private String title;
    //绑定内容id
    private Integer bindId;
    //类别
    private Integer contentType;
    //类别的CH
    private String contentTypeCh;
    //最后编辑
    private String updateUser;
    //更新时间
    private Date updateTime;

    public Integer getBindId() {
        return bindId;
    }

    public void setBindId(Integer bindId) {
        this.bindId = bindId;
    }

    public String getContentTypeCh() {
        return contentTypeCh;
    }

    public void setContentTypeCh(String contentTypeCh) {
        this.contentTypeCh = contentTypeCh;
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

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
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
}
