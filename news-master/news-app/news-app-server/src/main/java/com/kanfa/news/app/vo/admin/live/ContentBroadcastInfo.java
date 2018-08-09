package com.kanfa.news.app.vo.admin.live;

import com.alibaba.druid.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/28 10:31
 */
public class ContentBroadcastInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer Id;

    //直播标题
    private String title;
    //直播状态 broadcast_status 1:预告,2:直播,3:回顾
    private Integer broadcastStatus;
    //主播联系方式 broadcast_phone
    private String broadcastPhone;
    //发布状态 0待审核，1审核通过(上线)，2审核不通过(不上线)
    private Integer isPublish;
    //预告流url
    private String previewUrl;
    //直播流url
    private String liveUrl;
    //点播流url
    private String reviewUrl;

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

    //创建时间
    private Date createTime;

    private Integer isDelete;
    private Integer page;
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTitle() {
        return StringUtils.isEmpty(title) ? "" : title;
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

    public String getBroadcastPhone() {
        return StringUtils.isEmpty(broadcastPhone) ? "" : broadcastPhone;
    }

    public void setBroadcastPhone(String broadcastPhone) {
        this.broadcastPhone = broadcastPhone;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
