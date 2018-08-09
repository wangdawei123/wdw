package com.kanfa.news.app.vo.admin.video;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/2/28 11:10
 */
public class VideoTypeInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    //视频类型名称
    private String name;
    //创建人
    private String createUser;
    //创建日期
    private Date creatTime;
    //最后修改人
    private String updateUser;
    //最后更新日期
    private String updateTime;
    //是否上线(1:上线；0:下线)
    private Integer isPublish;


    public String getUpdateTime() {
        return StringUtils.isEmpty(updateTime) ? "" : updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return StringUtils.isEmpty(updateUser) ? "" : updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getCreateUser() {
        return StringUtils.isEmpty(createUser) ? "" : createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
