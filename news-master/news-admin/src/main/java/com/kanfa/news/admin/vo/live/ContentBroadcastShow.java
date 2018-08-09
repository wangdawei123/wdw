package com.kanfa.news.admin.vo.live;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/4/2 18:28
 */
public class ContentBroadcastShow implements Serializable {
    private Integer Id;

    //直播标题
    private String title;
    //直播状态 broadcast_status 1:预告,2:直播,3:回顾
    private Integer broadcastStatus;
    //主播联系方式 broadcast_phone
    private Long broadcastPhone;
    //预告流url
    private String previewUrl;
    //直播流url
    private String liveUrl;
    //点播流url
    private String reviewUrl;

    private Integer liveSourceChannel;

    public Integer getLiveSourceChannel() {
        return liveSourceChannel;
    }

    public void setLiveSourceChannel(Integer liveSourceChannel) {
        this.liveSourceChannel = liveSourceChannel;
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

    public Integer getBroadcastStatus() {
        return broadcastStatus;
    }

    public void setBroadcastStatus(Integer broadcastStatus) {
        this.broadcastStatus = broadcastStatus;
    }

    public Long getBroadcastPhone() {
        return broadcastPhone;
    }

    public void setBroadcastPhone(Long broadcastPhone) {
        this.broadcastPhone = broadcastPhone;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public String getReviewUrl() {
        return reviewUrl;
    }

    public void setReviewUrl(String reviewUrl) {
        this.reviewUrl = reviewUrl;
    }
}
