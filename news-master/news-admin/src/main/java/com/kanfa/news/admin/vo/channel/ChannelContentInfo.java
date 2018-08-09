package com.kanfa.news.admin.vo.channel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jiqc
 * @date 2018/3/29 18:36
 */
public class ChannelContentInfo implements Serializable {
    private Integer id;

    //频道ID
    private Integer channelId;

    //内容ID
    private Integer contentId;

    //排序值
    private Integer orderNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
