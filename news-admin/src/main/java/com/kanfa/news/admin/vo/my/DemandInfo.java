package com.kanfa.news.admin.vo.my;


import com.kanfa.news.admin.vo.kpi.KpiCountContentInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Chen
 * on 2018/5/15 16:43
 */
public class DemandInfo implements Serializable {

    private Integer id;
    //videomd
    private String videomd;
    //url
    private String url;
    //duration 时长
    private String duration;
    //medid
    private String medid;
    //title
    private String title;
    //create_time
    private Date createTime;
    //status 视频库状态  1：上传失败 2：上传完成 3：转码中 4：转码完成
    private Integer status;
    //create_uid
    private Integer createUid;
    //name
    private String name;
    //runid 运行id
    private Integer runid;
    //type 视频类型 １转载视频　２普通视频　３栏目视频　４动画视频
    private Integer type;
    //记者
    private List<Integer> cropUserIds;
    //xm_kpi_video  uid 人员id 工作类型 1视频编辑 2动画 3配音 6直播运营出境 7直播栏目运营主播
    //工作类型 1视频编辑 2动画 3配音 6直播运营出境 7直播栏目运营主播 work_type
    private List<KpiCountContentInfo> kpiCountList;

    public String getMedid() {
        return medid;
    }

    public void setMedid(String medid) {
        this.medid = medid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRunid() {
        return runid;
    }

    public void setRunid(Integer runid) {
        this.runid = runid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Integer> getCropUserIds() {
        return cropUserIds;
    }

    public void setCropUserIds(List<Integer> cropUserIds) {
        this.cropUserIds = cropUserIds;
    }

    public List<KpiCountContentInfo> getKpiCountList() {
        return kpiCountList;
    }

    public void setKpiCountList(List<KpiCountContentInfo> kpiCountList) {
        this.kpiCountList = kpiCountList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVideomd() {
        return videomd;
    }

    public void setVideomd(String videomd) {
        this.videomd = videomd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
