package com.kanfa.news.info.vo.admin.kpicount;

import java.io.Serializable;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/7/20 18:13
 */
public class ContentKpiCountInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String firstCheckTime;
    private Integer cid;
    private String title;
    private String createName;
    private String sourceType;
    private String isConnectVideo;
    private Integer videoId;
    private String workUsers;
    private String works;
    private Integer workTypeNum;
    private Double workScale;
    private String isOriginal;
    private String contentType;
    private String workType;
    private Integer imgNum;
    private Integer appPv;
    private Integer appUv;
    private String appScale;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstCheckTime() {
        return firstCheckTime;
    }

    public void setFirstCheckTime(String firstCheckTime) {
        this.firstCheckTime = firstCheckTime;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getIsConnectVideo() {
        return isConnectVideo;
    }

    public void setIsConnectVideo(String isConnectVideo) {
        this.isConnectVideo = isConnectVideo;
    }

    public String getWorkUsers() {
        return workUsers;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public void setWorkUsers(String workUsers) {
        this.workUsers = workUsers;
    }

    public Integer getWorkTypeNum() {
        return workTypeNum;
    }

    public void setWorkTypeNum(Integer workTypeNum) {
        this.workTypeNum = workTypeNum;
    }

    public Double getWorkScale() {
        return workScale;
    }

    public void setWorkScale(Double workScale) {
        this.workScale = workScale;
    }

    public String getWorks() {
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }

    public String getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(String isOriginal) {
        this.isOriginal = isOriginal;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Integer getImgNum() {
        return imgNum;
    }

    public void setImgNum(Integer imgNum) {
        this.imgNum = imgNum;
    }

    public Integer getAppPv() {
        return appPv;
    }

    public void setAppPv(Integer appPv) {
        this.appPv = appPv;
    }

    public String getAppScale() {
        return appScale;
    }

    public void setAppScale(String appScale) {
        this.appScale = appScale;
    }

    public Integer getAppUv() {
        return appUv;
    }

    public void setAppUv(Integer appUv) {
        this.appUv = appUv;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
}
