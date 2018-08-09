package com.kanfa.news.admin.vo.video;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;


/**
 * Created by Chen
 * on 2018/3/13 15:02
 */
public class VideoChannelFocusListInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    //kf_channel_focus中的id
    private Integer Id;
    //标题
    private String title;
    //最后编辑人
    private String updateUser;
    //更新时间
    private String updateTime;
    //是否发布 1 发布 0 未发布
    private Integer isPublish;

    //频道名字
    private String channelName;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

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
}
