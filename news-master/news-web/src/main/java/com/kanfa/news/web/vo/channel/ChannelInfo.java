package com.kanfa.news.web.vo.channel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jiqc
 * @date 2018/2/27 19:07
 */
public class ChannelInfo implements Serializable {

    private Integer id;

    //频道，1资讯（APP首页），2APP视频，3APPVR，4PC资讯
    private Integer category;

    //频道名
    private String name;

    //是否是始终显示，不可隐藏的，1：是，0：否
    private Integer isFixed;

    //置顶
    private Integer isTop;

    //发布状态。1：发布，0：未发布
    private Integer isPublish;

    //发布时间
    private Date publishTime;

    //创建人
    private String createUser;

    //创建时间
    private Date createTime;

    //状态，1：正常，0：删除
    private Integer channelStatus;

    //最后编辑人
    private String updateUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(Integer isFixed) {
        this.isFixed = isFixed;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
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

    public Integer getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(Integer channelStatus) {
        this.channelStatus = channelStatus;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
