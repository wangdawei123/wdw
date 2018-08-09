package com.kanfa.news.data.common.vo.content;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jiqc
 * @date 2018/3/5 15:58
 */
public class ContentOfSearchBindInfo implements Serializable{

    private Integer id;

    private String contentTypeName;

    //标题
    private String title;

    //更新时间
    private Date updateTime;

    //最后编辑人
    private String updateUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContentTypeName() {
        return contentTypeName;
    }

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
