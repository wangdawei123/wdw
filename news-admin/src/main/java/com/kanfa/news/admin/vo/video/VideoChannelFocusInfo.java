package com.kanfa.news.admin.vo.video;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/7 17:05
 */
public class VideoChannelFocusInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    //频道Id
    private Integer channelId;
    //频道名称
    private String name;

    private Integer focusCount;
    //创建时间
    private Date createTime;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(Integer focusCount) {
        this.focusCount = focusCount;
    }
}
