package com.kanfa.news.app.vo.admin.video;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/5 15:51
 */
public class VideoChannelInfo  implements Serializable{

    private static final long serialVersionUID = 1L;
    private Integer id;
    //频道名称
    private String name;
    //创建人
    private String createUser;
    //最后编辑人
    private  String updateUser;
    //创建时间
    private Date createTime;
    //发布状态。1：发布，0：未发布
    private Integer isPublish;
    //是否置顶。1：置顶，0：未置顶
    private Integer isTop;

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

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }
}
