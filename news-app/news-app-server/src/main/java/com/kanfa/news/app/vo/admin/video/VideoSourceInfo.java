package com.kanfa.news.app.vo.admin.video;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/5 13:50
 */
public class VideoSourceInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    //来源的id
    private Integer id;
    //来源的名字
    private String name;
    //创建人
    private String createUser;
    //创建时间
    private Date createTime;
    //最后修改人
    private String updateUser;
    //最后更新日期
    private String updateTime;

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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getUpdateUser() {
        return StringUtils.isEmpty(updateUser) ? "" : updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return StringUtils.isEmpty(updateTime) ? "" : updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
