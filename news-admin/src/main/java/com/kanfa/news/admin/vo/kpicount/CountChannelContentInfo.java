package com.kanfa.news.admin.vo.kpicount;

import java.io.Serializable;

/**
 * 渠道发稿量统计
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-20 11:56:05
 */
public class CountChannelContentInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String countDate;
    private String channelName;
    private Integer channelId;
    private Integer contentCount;

    public String getCountDate() {
        return countDate;
    }

    public void setCountDate(String countDate) {
        this.countDate = countDate;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getContentCount() {
        return contentCount;
    }

    public void setContentCount(Integer contentCount) {
        this.contentCount = contentCount;
    }
}


