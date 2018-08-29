package com.kanfa.news.app.vo.admin.live;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class LiveTypeInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    //直播类型名称
    private String name;

    //是否上线(1:上线；0:下线)
    private Integer isPublish;

    //类型状态
    private Integer status;

    //创建人
    private String createUser;

    //创建日期
    private Date createTime;

    //最后编辑人
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

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateUser() {
        return StringUtils.isEmpty(createUser) ? "" : createUser;
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
